package com.dzl.blog2;

import com.hankcs.hanlp.HanLP;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Blog2ApplicationTests {

    @Test
    void contextLoads() {
        String document = "  最近在找寻cms开源系统，目前找了几家，希望可以进行二次开发 ，找了一段时间，有一点收获把，记录一下\n" +
                "  ## 项目需求\n" +
                "  1:cms 系统、易用，代码清晰，开源，网站、小程序、后台管理 和当前技术栈向符合 （java）\n" +
                " ## 项目\n" +
                " 1. Mcms 基于springboot 框架 功能后台根据模板生成静态文件，有相应模板，自己做模板需要学习成本 mit协议，实际上mcms 还有 MStore（MS商城）是不开源和收费项目，但是主要业务文件都再maven 依赖里面，主要优势是模板多   [gitee链接](https://gitee.com/mingSoft/MCMS)\n" +
                "  2. Publiccms java 项目eclipse  2017及之前版本开源，2017版本是开源，mit许可的，可以二次开发，代码也不错，文档比较全，官网有使用模板的教学视频（1小时左右），如果有问题，加群提问反馈很快。但是模板比较少，就只有官方提供的几个模板，基本功能的都有了，但是如果自己对页面想大块的改动，要学习制作模板，还是要一点时间成本的 [gitee链接](https://gitee.com/sanluan/PublicCMS)\n" +
                "  3. Dotcms（国外） 收费了，目前qq邮箱和公司邮箱都获得不了试用许可\n" +
                "  4. Jeecms 目前再了解的系统，目前商用也是收费\n" +
                "  5. Gitee 目前看的的其他要不是收费，或者就是提交是一两年前，就不太考虑\n" +
                "  6. 这里再说一下wordpress ，这个当然不是java 是基于php ，主要是想说的wp里面有插件，可以支持做小程序 [链接](https://github.com/iamxjb/winxin-app-watch-life.net)  有提供一个免费插件 和一个小程序的开源系统。\n" +
                "  ## 总结\n" +
                "  现在国内外，开源的java cms系统，感觉还是比较少一些的。\n" +
                "  这些是比较主观的，可能有那些系统我没有找到，希望大家可以补充一下。\n";
        List<String> sentenceList = HanLP.extractSummary(document, 5);
        System.out.println(sentenceList);
    }

}
