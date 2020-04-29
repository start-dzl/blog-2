package com.dzl.blog2.model;

import com.dzl.blog2.entity.Article;
import com.dzl.blog2.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ArticlePlus extends Article {
    private List<Tag> tags;
}
