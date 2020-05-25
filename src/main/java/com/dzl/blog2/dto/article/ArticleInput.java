package com.dzl.blog2.dto.article;

import com.dzl.blog2.enums.PublishStatus;
import lombok.Data;

import java.util.List;


@Data
public class ArticleInput {

    private String title;

    private String author;

    private String articleContent;

    private String catalog;

    private List<String> tags;

    private PublishStatus publishStatus;
}
