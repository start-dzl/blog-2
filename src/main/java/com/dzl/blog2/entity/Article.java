package com.dzl.blog2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dzl.blog2.enums.CopyRight;
import com.dzl.blog2.enums.PublishStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime publishDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateDate;

    private String articleContent;

    private String articleTabloid;

    private String lastArticleId;

    private String nextArticleId;

    private Long likes;

    private Long playCount;

    private PublishStatus publishStatus;

    private CopyRight copyRight;

    private String catalogId;

    @TableField(exist = false)
    private Catalog catalog;

}
