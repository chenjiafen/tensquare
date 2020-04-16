package com.tensquare.search.dao;


import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    /**
     * 检索
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    public Page<Article> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
}
