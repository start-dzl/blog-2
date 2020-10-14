package com.dzl.blog2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.blog2.dto.article.ArticleInput;
import com.dzl.blog2.dto.article.ArticleSearchInput;
import com.dzl.blog2.dto.page.PageBody;
import com.dzl.blog2.entity.Article;
import com.dzl.blog2.model.ArticlePlus;


public interface IArticleService extends IService<Article> {
    Article createArticle(ArticleInput input);

    void deleteArticle(Long id);

    boolean changeArticle(Long id, ArticleInput input);

    PageBody<ArticlePlus> findAll(ArticleSearchInput input);

    PageBody<ArticlePlus> findAllBackend(ArticleSearchInput input);

    ArticlePlus detail(String id);
}
