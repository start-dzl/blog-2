package com.dzl.blog2.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String author;

    private LocalDateTime publishDate;

    private LocalDateTime updateDate;

    private String articleContent;

    private String articleTabloid;

    private Long lastArticleId;

    private Long nextArticleId;

    private Long likes;

}
