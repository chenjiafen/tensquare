package com.tensquare.base.service;


import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 添加标签
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");//设置id
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void  update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void delete(String id){
        labelDao.deleteById(id);
    }

    /**
     * 根据条件查询
     * @param label
     * @return
     */
    public List<Label> findByCondition(Label label){
        return labelDao.findAll(generteSpec(label));
    }

    /**
     * 分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */

    public PageResult<Label> findPageByCondition(Label label,Integer page,Integer size){
//        page-1页码是从0开始，表单传过来是1开始的
      Page pageBen= labelDao.findAll(generteSpec(label), PageRequest.of(page-1,size));
      //pageBen.getTotalElements()总记录数，pageBen.getContent()列表数
        return new PageResult<>(pageBen.getTotalElements(),pageBen.getContent());
    }

    /**
     * 提取公共方法
     * @return
     */
    private Specification<Label> generteSpec(Label label){
       return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();//保存后续拼装条件
                //labelname(标签名)!"".equals(searchMap.get("id")
                if(!StringUtils.isEmpty(label.getLabelname())&&!"".equals(label.getLabelname())){
                   Predicate p= criteriaBuilder.like(root.get("labelname").as(String.class),label.getLabelname());
                    predicateList.add(p);
                }
                //state条件（标签状态）
                if (!StringUtils.isEmpty(label.getState())){
                   Predicate p= criteriaBuilder.equal(root.get("state").as(String.class),label.getState());
                    predicateList.add(p);
                }
                //recommend条件（是否推荐）
                if (!StringUtils.isEmpty(label.getRecommend())){
                   Predicate p= criteriaBuilder.equal(root.get("recommend").as(String.class),label.getRecommend());
                    predicateList.add(p);
                }

                //若三个条件都没有
                if(predicateList.isEmpty()){
                    return  null;
                }
                //所有条件都有
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
