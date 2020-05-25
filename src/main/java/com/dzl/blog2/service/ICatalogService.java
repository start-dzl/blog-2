package com.dzl.blog2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.blog2.entity.Catalog;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-04-28
 */
public interface ICatalogService extends IService<Catalog> {
    Catalog createCatalog(String name);

    void deleteCatalog(String id);

    Catalog changeCatalog(String id, String name);

    List<Catalog> findByName(String name);

}
