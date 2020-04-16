package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    /**
     * 查询所有吐槽信息
     *
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Spit> spitList = spitService.findAll ();
        return new Result ( StatusCode.OK, true, "查询成功", spitList );

    }

    /**
     * 根据id查询吐槽信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        Spit spit = spitService.findById ( id );
        return new Result ( StatusCode.OK, true, "查询成功", spit );
    }

    /**
     * 新增成功
     *
     * @param spit
     * @return
     */

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.save ( spit );
        return new Result ( StatusCode.OK, true, "新增成功" );
    }

    /**
     * 修改
     *
     * @param id
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable String id, @RequestBody Spit spit) {
        spit.set_id ( id );
        spitService.update ( spit );
        return new Result ( StatusCode.OK, true, "修改成功" );
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        spitService.deleteById ( id );
        return new Result ( StatusCode.OK, true, "删除成功" );
    }



    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据父级id分页查询吐槽
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentid, @PathVariable Integer page, @PathVariable Integer size) {
        Page<Spit> pageBean = spitService.findByParentId ( parentid, page, size );
        return new Result ( StatusCode.OK, true, "查询成功", new PageResult<Spit> ( pageBean.getTotalElements (), pageBean.getContent () ) );
    }

    /**
     * 点赞
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId) {
        //获取当前用户登录的id
        String userId="666";
        //从redis判断当前用户是否已经点赞
        String flag= (String) redisTemplate.opsForValue ().get ("thumbup_"+userId+"_"+ spitId);
            //点过=》返回错误信息
        if ("1".equals ( flag )){
            //点过=》返回错误信息
            return new Result ( StatusCode.REPERROR,false,"请勿重复点赞" );
        }else{
            //未点过==>调用service点赞，
            spitService.thumbup ( spitId );
//            将点赞记录存入redis
            redisTemplate.opsForValue ().set ("thumbup_"+userId+"_"+ spitId,"1");

            return new Result ( StatusCode.OK,true,"点赞成功" );

        }


    }
}
