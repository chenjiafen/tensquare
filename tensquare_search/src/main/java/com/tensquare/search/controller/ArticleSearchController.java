package com.tensquare.search.controller;


import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleSearchService.add(article);
        return  new Result(StatusCode.OK,true,"增加成功");

    }

    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findByTitleLike(@PathVariable String keywords,@PathVariable Integer page,@PathVariable Integer size){

       Page<Article>  articlePage=articleSearchService.findByTitleLike(keywords,page,size);
        return new Result(StatusCode.OK,true,"查询成功",new PageResult<Article>(articlePage.getTotalElements(),articlePage.getContent()));
    }

}
