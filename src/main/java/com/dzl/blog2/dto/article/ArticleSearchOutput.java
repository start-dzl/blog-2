package com.dzl.blog2.dto.article;

import lombok.Data;


@Data
public class ArticleSearchOutput {

    private String keyWord;

    private Integer current;

    private Integer size;
}
