package com.dzl.blog2.controller;

import com.dzl.blog2.dto.article.ArticleInput;
import com.dzl.blog2.exception.ResultBody;
import com.dzl.blog2.model.ArticlePlus;
import com.dzl.blog2.service.IArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService iArticleService;

    @ApiOperation(value = "生成文章")
    @GetMapping("/create")
    public ResultBody Create(@Valid ArticleInput input) {
        Long id = iArticleService.createArticle(input).getId();
        return ResultBody.success(id.toString());
    }

    @ApiOperation(value = "文章列表")
    @GetMapping("/list")
    public List<ArticlePlus> list() {
        return iArticleService.findAll();
    }
}
