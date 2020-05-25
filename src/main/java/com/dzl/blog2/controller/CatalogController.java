package com.dzl.blog2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dzl.blog2.entity.Catalog;
import com.dzl.blog2.exception.ResultBody;
import com.dzl.blog2.service.ICatalogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/tag")
public class CatalogController {
    @Autowired
    private ICatalogService iCatalogService;

    @ApiOperation(value = "生成分类")
    @PostMapping("/create")
    public Catalog Create(@RequestParam String name) {

        return iCatalogService.createCatalog(name);
    }

    @ApiOperation(value = "修改分类")
    @PutMapping("/change/{id}")
    public Catalog change(@PathVariable String id, @RequestParam String name) {

        return iCatalogService.changeCatalog(id, name);
    }

    @ApiOperation(value = "查找分类")
    @GetMapping("/list")
    public List<Catalog> findList(@RequestParam String name) {

        return iCatalogService.findByName(name);
    }

    @ApiOperation(value = "所有分类")
    @GetMapping("/list/all")
    public List<Catalog> findAll() {

        return iCatalogService.getBaseMapper().selectList(new QueryWrapper<>());
    }


    @ApiOperation(value = "删除分类")
    @DeleteMapping("/delete/{id}")
    public ResultBody delete(@PathVariable String id) {
        iCatalogService.deleteCatalog(id);
        return ResultBody.success();
    }
}
