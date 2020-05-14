package com.dzl.blog2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dzl.blog2.entity.Tag;
import com.dzl.blog2.exception.ResultBody;
import com.dzl.blog2.service.ITagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/tag")
public class TagController {
    @Autowired
    private ITagService iTagService;

    @ApiOperation(value = "生成标签")
    @PostMapping("/create")
    public Tag Create(@RequestParam String name) {

        return iTagService.createTag(name);
    }

    @ApiOperation(value = "修改标签")
    @PutMapping("/change/{id}")
    public Tag change(@PathVariable String id, @RequestParam String name) {

        return iTagService.changeTag(id, name);
    }

    @ApiOperation(value = "查找标签")
    @GetMapping("/list")
    public List<Tag> findList(@RequestParam String name) {

        return iTagService.findByName(name);
    }

    @ApiOperation(value = "所有标签")
    @GetMapping("/list/all")
    public List<Tag> findAll() {

        return iTagService.getBaseMapper().selectList(new QueryWrapper<Tag>());
    }


    @ApiOperation(value = "删除标签")
    @DeleteMapping("/delete/{id}")
    public ResultBody delete(@PathVariable String id) {
        iTagService.deleteTag(id);
        return ResultBody.success();
    }
}
