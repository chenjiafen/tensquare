package com.tensquare.article.controller;

import java.util.List;
import java.util.Map;

import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;

import entity.PageResult;
import entity.Result;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", articleService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(StatusCode.OK, true, "查询成功", articleService.findById(id));

    }


    /**
     * 分页查询全部数据
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/{page}/{size}", method = RequestMethod.GET)
    public Result findPage(@PathVariable int page, @PathVariable int size) {
        Page<Article> pageList = articleService.findPage(page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()));

    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Article> pageList = articleService.findSearch(searchMap, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 增加
     *
     * @param article
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(StatusCode.OK, true, "增加成功");

    }

    /**
     * 修改
     *
     * @param article
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleService.update(article);
        return new Result(StatusCode.OK, true, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        articleService.deleteById(id);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /**
     * 审核
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/examine/{id}", method = RequestMethod.PUT)
    public Result examine(@PathVariable String id) {
        articleService.examine(id);
        return new Result(StatusCode.OK, true, "审核成功");
    }

    /**
     * 点赞
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public Result tte(@PathVariable String id) {
        articleService.Thumbup(id);
        return new Result(StatusCode.OK, true, "点赞+1");
    }
}
