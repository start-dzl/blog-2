package com.dzl.blog2.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Tag extends Model<Tag> {

    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 标签名
     */
    private String name;


}
