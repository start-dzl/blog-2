package com.dzl.blog2.dto.article;

import lombok.Data;

import java.util.List;


@Data
public class ArticleInput {

    private String title;

    private String author;

    private String articleContent;

    private List<String> tags;
}
