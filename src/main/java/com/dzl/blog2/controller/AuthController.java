package com.dzl.blog2.controller;

import com.dzl.blog2.service.IUserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private IUserService adminService;

  /*  @PostMapping("/sign")
    @ApiOperation(value = "登录")
    public JwtAuthenticationResponse sign(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        User admin = adminService.findByPhoneName(loginRequest.getPhoneName());
        if (admin == null) {
            throw new BizException("-1", "用户不存在");
        }
        MD5Util md5Util = new MD5Util();
        if (!(md5Util.encode(loginRequest.getPassword())).equals(admin.getPassword())) {
            throw new BizException("-1", "账号或密码错误");
        }
        logger.info("login a admin whose PhoneName:{}", loginRequest.getPhoneName());
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse(tokenProvider.token(loginRequest.getPhoneName(), ADMIN));
        Cookie token = new Cookie("token", authenticationResponse.getAccessToken());
        token.setPath("/");
        Cookie tokenType = new Cookie("tokenType", authenticationResponse.getTokenType());
        tokenType.setPath("/");
        response.addCookie(token);
        response.addCookie(tokenType);
        return authenticationResponse;
    }*/
}
