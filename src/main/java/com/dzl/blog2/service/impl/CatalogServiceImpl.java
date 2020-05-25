package com.dzl.blog2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.blog2.entity.Catalog;
import com.dzl.blog2.exception.BizException;
import com.dzl.blog2.mapper.CatalogMapper;
import com.dzl.blog2.service.ICatalogService;
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
public class CatalogServiceImpl extends ServiceImpl<CatalogMapper, Catalog> implements ICatalogService {


    @Override
    public Catalog createCatalog(String name) {
        List<Catalog> catalogs = getBaseMapper().selectList(new QueryWrapper<Catalog>().lambda().eq(Catalog::getName, name));
        if (catalogs.size() == 0) {
            Catalog catalog = new Catalog();
            catalog.setName(name);
            catalog.insert();
            return catalog;
        }
        return catalogs.get(0);
    }

    @Override
    public void deleteCatalog(String id) {
        getBaseMapper().deleteById(id);
    }

    @Override
    public Catalog changeCatalog(String id, String name) {
        List<Catalog> catalogs = getBaseMapper().selectList(new QueryWrapper<Catalog>().lambda().eq(Catalog::getName, name));
        if (catalogs.size() > 0) {
            throw new BizException("-1", "修改后的标签已经存在");
        }
        Catalog catalog = getBaseMapper().selectById(id);
        catalog.setName(name);
        getBaseMapper().insert(catalog);
        return catalog;
    }

    @Override
    public List<Catalog> findByName(String name) {
        return getBaseMapper().selectList(new QueryWrapper<Catalog>().lambda().like(Catalog::getName, name));
    }
}
