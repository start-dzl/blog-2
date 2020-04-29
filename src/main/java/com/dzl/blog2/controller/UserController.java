package com.dzl.blog2.controller;


import com.dzl.blog2.dto.user.UserInput;
import com.dzl.blog2.exception.ResultBody;
import com.dzl.blog2.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author dengzl
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "生成用户")
    @GetMapping("/create")
    public ResultBody Create(@Valid UserInput input) {

        return ResultBody.success(iUserService.createUser(input).getId());
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public ResultBody delete(@PathVariable Long id) {

        iUserService.deleteUser(id);
        return ResultBody.success();
    }


}
