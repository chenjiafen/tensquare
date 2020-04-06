package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        List<Spit> spitList = spitDao.findAll();
        return spitList;
    }

    /**
     * 根据id查询spit
     * @param id
     * @return
     */
    public Spit findById(String id) {
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /**
     * 新增
     * @param spit
     */
    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+""); //设置主键
        spitDao.save(spit);
    }

    /**
     * 修改
     * @param spit
     */
    public  void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public  void  deleteById(String id){
        spitDao.deleteById(id);

    }
}
