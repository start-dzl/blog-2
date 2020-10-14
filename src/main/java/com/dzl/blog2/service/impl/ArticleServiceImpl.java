package com.dzl.blog2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.blog2.dto.article.ArticleInput;
import com.dzl.blog2.dto.article.ArticleSearchInput;
import com.dzl.blog2.dto.page.PageBody;
import com.dzl.blog2.entity.Article;
import com.dzl.blog2.entity.ArticleTag;
import com.dzl.blog2.entity.Catalog;
import com.dzl.blog2.entity.Tag;
import com.dzl.blog2.enums.PublishStatus;
import com.dzl.blog2.exception.BizException;
import com.dzl.blog2.mapper.ArticleMapper;
import com.dzl.blog2.model.ArticlePlus;
import com.dzl.blog2.service.IArticleService;
import com.dzl.blog2.service.IArticleTagService;
import com.dzl.blog2.service.ICatalogService;
import com.dzl.blog2.service.ITagService;
import com.dzl.blog2.utils.BuildArticleTabloidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ITagService iTagService;

    @Autowired
    private IArticleTagService iArticleTagService;

    @Autowired
    private ICatalogService iCatalogService;

    @Override
    public Article createArticle(ArticleInput input) {
        if (input.getTitle().isEmpty())
            throw new BizException("-1", "标题不为空");
        List<Article> articles = baseMapper.selectList(new QueryWrapper<Article>().lambda().eq(Article::getTitle, input.getTitle()));
        if (articles.size() > 0) {
            throw new BizException("-1", "文章不允许重复标题");
        }
        List<Tag> tags = new ArrayList<>();
        if (input.getTags() != null)
            tags = iTagService.getBaseMapper().selectBatchIds(input.getTags());

        Article lastArticle = getBaseMapper().findLastArticle();
        Article article = new Article();
        article.setTitle(input.getTitle());
        article.setArticleContent(input.getArticleContent());
        if (lastArticle != null) {
            article.setLastArticleId(lastArticle.getId());
        }
        //关联分类
        Catalog catalog = iCatalogService.getBaseMapper().selectById(input.getCatalog());
        if (catalog != null)
            article.setCatalogId(catalog.getId());
        article.setPublishDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setAuthor(input.getAuthor());
        article.setPublishStatus(input.getPublishStatus());
        //获得文章html代码并生成摘要
        BuildArticleTabloidUtil buildArticleTabloidUtil = new BuildArticleTabloidUtil();
        article.setArticleTabloid(buildArticleTabloidUtil.buildArticleTabloidV2(input.getArticleContent()));
        article.insert();
        if (lastArticle != null) {
            lastArticle.setNextArticleId(article.getId());
            lastArticle.updateById();
        }
        createArticleTag(article, tags);
        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        //先删除中间表
        iArticleTagService.getBaseMapper().delete(new QueryWrapper<ArticleTag>().lambda().eq(ArticleTag::getArticleId, id));
        getBaseMapper().deleteById(id);

    }

    @Override
    public boolean changeArticle(Long id, ArticleInput input) {
        Article article = getBaseMapper().selectById(id);
        boolean isChange = false;
        if (!input.getTitle().isBlank()) {
            article.setTitle(input.getTitle());
            isChange = true;
        }
        if (!input.getAuthor().isBlank()) {
            article.setAuthor(input.getAuthor());
            isChange = true;
        }
        if (!input.getArticleContent().isBlank()) {
            article.setArticleContent(input.getArticleContent());
            //获得文章html代码并生成摘要
            BuildArticleTabloidUtil buildArticleTabloidUtil = new BuildArticleTabloidUtil();
            article.setArticleTabloid(buildArticleTabloidUtil.buildArticleTabloidV2(input.getArticleContent()));
            isChange = true;
        }
        List<Tag> tags = iTagService.getBaseMapper().selectBatchIds(input.getTags());
        if (tags.size() > 0) {
            //先删除中间表
            iArticleTagService.getBaseMapper().delete(new QueryWrapper<ArticleTag>().lambda().eq(ArticleTag::getArticleId, id));
            createArticleTag(article, tags);
        }
        if (isChange) {
            article.setUpdateDate(LocalDateTime.now());
        }
        return article.insertOrUpdate();
    }

    private void createArticleTag(Article article, List<Tag> tags) {
        for (Tag tag : tags) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(tag.getId());
            articleTag.insert();
        }
    }

    @Override
    public PageBody<ArticlePlus> findAll(ArticleSearchInput input) {
        IPage<ArticlePlus> page = new Page<>(input.getCurrent(), input.getSize());
        //设置只显示发布
        input.setPublishStatus(PublishStatus.PUBLISH);
        List<ArticlePlus> allArticle = getBaseMapper().findAllArticle(input.getKeyWord(), input.getPublishStatus().getValue(), page);
        return new PageBody<>(page, allArticle);
    }

    @Override
    public PageBody<ArticlePlus> findAllBackend(ArticleSearchInput input) {
        IPage<ArticlePlus> page = new Page<>(input.getCurrent(), input.getSize());
        Integer integer = null;
        if (input.getPublishStatus() != null) {
            integer = input.getPublishStatus().getValue();
        }

        List<ArticlePlus> allArticle = getBaseMapper().findAllArticle(input.getKeyWord(), integer, page);
        return new PageBody<>(page, allArticle);
    }

    @Override
    public ArticlePlus detail(String id) {
        ArticleMapper baseMapper = getBaseMapper();
        ArticlePlus articlePlus = baseMapper.findArticlePlusById(id);
        articlePlus.setPlayCount(articlePlus.getPlayCount() + 1);
        articlePlus.insertOrUpdate();

        return articlePlus;
    }
}
