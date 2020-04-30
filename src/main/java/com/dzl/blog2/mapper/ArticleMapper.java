package com.dzl.blog2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dzl.blog2.entity.Article;
import com.dzl.blog2.model.ArticlePlus;
import org.apache.ibatis.annotations.Param;

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

    List<ArticlePlus> findAllArticle(@Param("key_word") String keyWord, IPage<ArticlePlus> myPage);

    ArticlePlus findArticlePlusById(@Param("id") String id);

}
