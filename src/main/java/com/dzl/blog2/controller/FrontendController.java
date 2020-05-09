package com.dzl.blog2.controller;

import com.dzl.blog2.dto.article.ArticleSearchInput;
import com.dzl.blog2.dto.page.PageBody;
import com.dzl.blog2.model.ArticlePlus;
import com.dzl.blog2.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontendController {

    @Autowired
    private IArticleService iArticleService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * @return 查询全部信息
     */
    @RequestMapping("/list")
    public ModelAndView itemsList() {
        ArticleSearchInput articleSearchInput = new ArticleSearchInput();
        articleSearchInput.setCurrent(1);
        articleSearchInput.setSize(3);
        PageBody<ArticlePlus> all = iArticleService.findAll(articleSearchInput);
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("list", all.getContent());
        return mav;
    }

    /**
     * @return 查询全部信息
     */
    @RequestMapping("/index")
    public String itemsList2(Model model) {
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
    @RequestMapping("/refresh")
    public String refresh(Model model) {
        ArticleSearchInput articleSearchInput = new ArticleSearchInput();
        articleSearchInput.setCurrent(2);
        articleSearchInput.setSize(5);
        PageBody<ArticlePlus> all = iArticleService.findAll(articleSearchInput);
        model.addAttribute("pages", all);
        return "index::table_refresh";
    }
}
