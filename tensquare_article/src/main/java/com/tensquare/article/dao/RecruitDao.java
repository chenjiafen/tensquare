package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    /**
     * 推荐职位列表,需求分析：查询状态为2并以创建日期降序排序，查询前4条记录
     * 查询最新职位列表(按创建日期降序排序),查询前4条记录
     *
     * @return
     */

    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);


    /**
     * 需求分析：查询状态不为0并以创建日期降序排序，查询前12条记录
     */
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);

}
