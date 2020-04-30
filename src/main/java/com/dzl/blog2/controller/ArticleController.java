package com.dzl.blog2.controller;

import com.dzl.blog2.dto.article.ArticleInput;
import com.dzl.blog2.dto.article.ArticleSearchInput;
import com.dzl.blog2.dto.page.PageBody;
import com.dzl.blog2.exception.ResultBody;
import com.dzl.blog2.model.ArticlePlus;
import com.dzl.blog2.service.IArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PostMapping("/create")
    public ResultBody Create(@RequestBody ArticleInput input) {
        String id = iArticleService.createArticle(input).getId();
        return ResultBody.success(id);
    }

    @ApiOperation(value = "文章详情")
    @GetMapping("/{id}")
    public ArticlePlus detail(@PathVariable String id) {
        return iArticleService.detail(id);
    }

    @ApiOperation(value = "文章列表")
    @GetMapping("/list")
    public PageBody<ArticlePlus> list(@Valid ArticleSearchInput input) {
        return iArticleService.findAll(input);
    }

    @ApiOperation(value = "修改文章")
    @PutMapping("/change/{id}")
    public ResultBody change(@PathVariable Long id, @RequestBody ArticleInput input) {
        return ResultBody.success(iArticleService.changeArticle(id, input));
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("/delete/{id}")
    public ResultBody delete(@PathVariable Long id) {
        iArticleService.deleteArticle(id);
        return ResultBody.success();
    }
}
