package com.dzl.blog2.controller;


import com.dzl.blog2.entity.Tag;
import com.dzl.blog2.exception.ResultBody;
import com.dzl.blog2.service.ITagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private ITagService iTagService;

    @ApiOperation(value = "生成标签")
    @GetMapping("/create")
    public Tag Create(@RequestParam String name) {

        return iTagService.createTag(name);
    }

    @ApiOperation(value = "修改标签")
    @PutMapping("/change/{id}")
    public Tag change(@PathVariable Long id, @RequestParam String name) {

        return iTagService.changeTag(id, name);
    }

    @ApiOperation(value = "修改标签")
    @GetMapping("/list")
    public List<Tag> findList(@RequestParam String name) {

        return iTagService.findByName(name);
    }

    @ApiOperation(value = "删除标签")
    @DeleteMapping("/delete/{id}")
    public ResultBody delete(@PathVariable Long id) {
        iTagService.deleteTag(id);
        return ResultBody.success();
    }
}
