package com.dzl.blog2.controller;

import com.dzl.blog2.dto.article.ArticleSearchInput;
import com.dzl.blog2.dto.page.PageBody;
import com.dzl.blog2.model.ArticlePlus;
import com.dzl.blog2.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
public class FrontendController {

    @Autowired
    private IArticleService iArticleService;

    @GetMapping("/")
    public String index(Model model) {
        ArticleSearchInput articleSearchInput = new ArticleSearchInput();
        articleSearchInput.setCurrent(1);
        articleSearchInput.setSize(5);
        PageBody<ArticlePlus> all = iArticleService.findAll(articleSearchInput);
        model.addAttribute("pages", all);
        return "index";
    }

    /**
     * @return 查询全部信息
     */
    @GetMapping("/refresh/article")
    public String refresh(Model model, @Valid ArticleSearchInput input) {
        PageBody<ArticlePlus> all = iArticleService.findAll(input);
        model.addAttribute("pages", all);
        return "index::table_refresh";
    }
}
