package com.dzl.blog2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzl.blog2.entity.Article;
import com.dzl.blog2.model.ArticlePlus;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dzl
 * @since 2020-04-27
 */
public interface ArticleMapper extends BaseMapper<Article> {

    Article findLastArticle();

    List<ArticlePlus> findAllArticle();

}
