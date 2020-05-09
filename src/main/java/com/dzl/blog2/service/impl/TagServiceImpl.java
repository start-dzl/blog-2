package com.dzl.blog2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.blog2.entity.Tag;
import com.dzl.blog2.exception.BizException;
import com.dzl.blog2.mapper.TagMapper;
import com.dzl.blog2.service.ITagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-04-28
 */
@Service
@Transactional
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {


    @Override
    public Tag createTag(String name) {
        List<Tag> tags = getBaseMapper().selectList(new QueryWrapper<Tag>().lambda().eq(Tag::getName, name));
        if (tags.size() == 0) {
            Tag tag = new Tag();
            tag.setName(name);
            tag.insert();
            return tag;
        }
        return tags.get(0);
    }

    @Override
    public void deleteTag(String id) {
        getBaseMapper().deleteById(id);
    }

    @Override
    public Tag changeTag(String id, String name) {
        List<Tag> tags = getBaseMapper().selectList(new QueryWrapper<Tag>().lambda().eq(Tag::getName, name));
        if (tags.size() > 0) {
            throw new BizException("-1", "修改后的标签已经存在");
        }
        Tag tag = getBaseMapper().selectById(id);
        tag.setName(name);
        getBaseMapper().insert(tag);
        return tag;
    }

    @Override
    public List<Tag> findByName(String name) {
        return getBaseMapper().selectList(new QueryWrapper<Tag>().lambda().like(Tag::getName, name));
    }
}
