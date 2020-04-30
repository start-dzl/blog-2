package com.dzl.blog2.dto.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 分页统一返回数据结构
 *
 * @param <T>
 */

@Data
public class PageBody<T> {
    private Long page;

    private Long size;

    private Long total;

    private Long current;

    private List<T> content;

    public PageBody(IPage page, List<T> content) {
        this.page = page.getPages();
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.content = content;
    }

}
