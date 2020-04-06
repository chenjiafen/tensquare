package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param id
     * @param spit
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Spit spit) {
        spit.set_id ( id );
        spitService.update ( spit );
        return new Result ( StatusCode.OK, true, "修改成功" );
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        spitService.deleteById (id);
        return  new Result ( StatusCode.OK,true,"删除成功" );
    }
}
