package com.dzl.blog2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.blog2.dto.article.ArticleInput;
import com.dzl.blog2.entity.Article;
import com.dzl.blog2.model.ArticlePlus;

import java.util.List;


public interface IArticleService extends IService<Article> {
    Article createArticle(ArticleInput input);

    void deleteArticle(Long id);

    Article changeArticle(Long id, String name);

    List<Article> findByName(String name);

    List<ArticlePlus> findAll();
}
