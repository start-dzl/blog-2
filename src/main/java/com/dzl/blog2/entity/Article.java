package com.dzl.blog2.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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

    private LocalDateTime publishDate;

    private LocalDateTime updateDate;

    private String articleContent;

    private String articleTabloid;

    private String lastArticleId;

    private String nextArticleId;

    private Long likes;

    private Long playCount;

/*    private String idStr;

    public String getIdStr() {
        return id.toString();
    }*/
}
