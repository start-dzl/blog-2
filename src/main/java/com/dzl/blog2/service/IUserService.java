package com.dzl.blog2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.blog2.dto.user.UserInput;
import com.dzl.blog2.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-04-27
 */
public interface IUserService extends IService<User> {

    User createUser(UserInput input);

    void deleteUser(String id);

    User detail(String id);

    User findByPhoneName(String phoneName);
}
