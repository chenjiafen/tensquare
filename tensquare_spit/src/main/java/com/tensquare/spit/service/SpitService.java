package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
//@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部
     *
     * @return
     */
    public List<Spit> findAll() {
        List<Spit> spitList = spitDao.findAll ();
        return spitList;
    }

    /**
     * 根据id查询spit
     *
     * @param id
     * @return
     */
    public Spit findById(String id) {
        Spit spit = spitDao.findById ( id ).get ();
        return spit;
    }

    /**
     * 新增
     *
     * @param spit
     */
    public void save(Spit spit) {
        spit.set_id ( idWorker.nextId () + "" ); //设置主键
        spitDao.save ( spit );
    }

    /**
     * 修改
     *
     * @param spit
     */
    public void update(Spit spit) {
        spitDao.save ( spit );
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        spitDao.deleteById ( id );

    }

    /**
     * 根据父级id分页查询吐槽
     *
     * @return
     */
    public Page<Spit> findByParentId(String parentid, Integer page, Integer size) {
//        PageRequest pageRequest = PageRequest.of ( page - 1, size );
        return spitDao.findByParentid ( parentid, PageRequest.of ( page - 1, size ) );
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    //吐槽点赞
    public void thumbup(String spitId){
        //根据id查询
        Query query=new Query();
        query.addCriteria ( Criteria.where ( "_id" ).is (spitId) );//添加条件

        //修改原有thumbup值为原有值+1
        Update update=new Update ();
        update.inc ( "thumbup",1);
        mongoTemplate.updateFirst (query ,update, "spit");

    }

}
