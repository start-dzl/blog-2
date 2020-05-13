package com.dzl.blog2.controller;


import com.dzl.blog2.dto.user.UserInput;
import com.dzl.blog2.entity.User;
import com.dzl.blog2.exception.ResultBody;
import com.dzl.blog2.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author dengzl
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/api/v0/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "生成用户")
    @PostMapping("/create")
    public ResultBody Create(@RequestBody UserInput input) {

        return ResultBody.success(iUserService.createUser(input).getId());
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public ResultBody delete(@PathVariable String id) {

        iUserService.deleteUser(id);
        return ResultBody.success();
    }

    @ApiOperation(value = "用户详情")
    @GetMapping("/{id}")
    public User detail(@PathVariable String id) {
        return iUserService.detail(id);
    }


}
