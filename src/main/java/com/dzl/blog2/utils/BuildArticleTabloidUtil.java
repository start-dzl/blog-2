package com.dzl.blog2.utils;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class BuildArticleTabloidUtil {

    public String buildArticleTabloidV2(String htmlArticleComment) {
        MutableDataSet options = new MutableDataSet();

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(htmlArticleComment);
        String html = renderer.render(document);
        String myArticleTabloid = convert(html);
        if (myArticleTabloid.length() > 300) {
            myArticleTabloid = myArticleTabloid.substring(0, 300);
        }

        return myArticleTabloid;
    }

    public String buildArticleTabloidV1(String htmlArticleComment) {

        String regex = "\\s+";
        String str = htmlArticleComment.trim();
        //去掉所有空格
        String articleTabloid = str.replaceAll(regex, "");

        int beginIndex = articleTabloid.indexOf("<");
        int endIndex = articleTabloid.indexOf(">");
        String myArticleTabloid = "";
        String nowStr = "";
        while (beginIndex != -1) {
            nowStr = articleTabloid.substring(0, beginIndex);
            if (nowStr.length() > 197) {
                nowStr = nowStr.substring(0, 197);
                myArticleTabloid += nowStr;
            } else {
                myArticleTabloid += nowStr;
            }

            articleTabloid = articleTabloid.substring(endIndex + 1);
            beginIndex = articleTabloid.indexOf("<");
            if (myArticleTabloid.length() < 197) {
                //过滤掉<pre>标签中的代码块
                if (articleTabloid.length() > 4) {
                    if (articleTabloid.charAt(beginIndex) == '<' && articleTabloid.charAt(beginIndex + 1) == 'p' && articleTabloid.charAt(beginIndex + 2) == 'r' && articleTabloid.charAt(beginIndex + 3) == 'e') {
                        endIndex = articleTabloid.indexOf("</pre>");
                        endIndex = endIndex + 5;
                    } else {
                        endIndex = articleTabloid.indexOf(">");
                    }
                } else {
                    endIndex = articleTabloid.indexOf(">");
                }
            } else {
                break;
            }

        }

        if (myArticleTabloid.length() > 197) {
            myArticleTabloid = myArticleTabloid.substring(0, 197);
        }

        return myArticleTabloid;
    }

    public String convert(String html) {
        if (StringUtils.isEmpty(html)) {
            return "";
        }

        Document document = Jsoup.parse(html);
        Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
        document.outputSettings(outputSettings);
        document.select("br").append("\\n");
        document.select("p").prepend("\\n");
        document.select("p").append("\\n");
        String newHtml = document.html().replaceAll("\\\\n", "\n");
        String plainText = Jsoup.clean(newHtml, "", Whitelist.none(), outputSettings);
        String result = StringEscapeUtils.unescapeHtml(plainText.trim());
        return result;
    }
}
