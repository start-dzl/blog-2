package com.dzl.blog2.dto.article;

import com.dzl.blog2.enums.PublishStatus;
import lombok.Data;


@Data
public class ArticleSearchInput {

    private String keyWord;

    private Integer current;

    private Integer size;

    private PublishStatus publishStatus;
}
