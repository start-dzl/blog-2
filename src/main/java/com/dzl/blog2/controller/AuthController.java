package com.dzl.blog2.controller;

import com.dzl.blog2.config.security.JwtTokenProvider;
import com.dzl.blog2.dto.user.JwtAuthenticationResponse;
import com.dzl.blog2.dto.user.LoginRequest;
import com.dzl.blog2.entity.User;
import com.dzl.blog2.exception.BizException;
import com.dzl.blog2.service.IUserService;
import com.dzl.blog2.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.dzl.blog2.enums.AccessTypeEnum.ADMIN;

/**
 * 登录认证
 *
 * @author yu
 */
@RestController
@RequestMapping("/api/auth")
@Api(tags = "后台管理 - 认证")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private IUserService adminService;

    @PostMapping("/sign")
    @ApiOperation(value = "登录")
    public JwtAuthenticationResponse sign(@Valid @RequestBody LoginRequest loginRequest) {

        User admin = adminService.findByPhoneName(loginRequest.getPhoneName());
        if (admin == null) {
            throw new BizException("-1", "用户不存在");
        }
        MD5Util md5Util = new MD5Util();
        if (!(md5Util.encode(loginRequest.getPassword())).equals(admin.getPassword())) {
            throw new BizException("-1", "账号或密码错误");
        }
        logger.info("login a admin whose PhoneName:{}", loginRequest.getPhoneName());

        return new JwtAuthenticationResponse(tokenProvider.token(loginRequest.getPhoneName(), ADMIN));
    }
}
