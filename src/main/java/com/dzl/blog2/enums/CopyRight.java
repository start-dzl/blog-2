package com.dzl.blog2.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum CopyRight implements IEnum<Integer> {
    PUBLISH(0, "原创"),
    DRAFT(1, "转载");

    private final int value;
    private final String desc;

    CopyRight(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
