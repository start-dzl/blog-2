package com.dzl.blog2.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

@Getter
public enum PublishStatus implements IEnum<Integer> {
    PUBLISH(0, "已发布"),
    DRAFT(1, "草稿");

    private final int value;
    private final String desc;

    PublishStatus(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}