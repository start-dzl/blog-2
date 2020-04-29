package com.dzl.blog2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.blog2.entity.Tag;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-04-28
 */
public interface ITagService extends IService<Tag> {
    Tag createTag(String name);

    void deleteTag(Long id);

    Tag changeTag(Long id, String name);

    List<Tag> findByName(String name);

}
