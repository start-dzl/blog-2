package com.dzl.blog2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.blog2.dto.user.UserInput;
import com.dzl.blog2.entity.User;
import com.dzl.blog2.exception.BizException;
import com.dzl.blog2.mapper.UserMapper;
import com.dzl.blog2.service.IUserService;
import com.dzl.blog2.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-04-27
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User createUser(UserInput input) {
        MD5Util md5Util = new MD5Util();
        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, input.getPhone()));
        if (user != null) {
            String encode = md5Util.encode(input.getPassword());
            if (encode.equals(user.getPassword())) {
                return user;
            }
            throw new BizException("-1", "当前电话号码已经注册");
        } else {
            User newUser = new User();
            newUser.setName(input.getUsername());
            newUser.setAvatar(input.getAvatar());
            newUser.setPassword(md5Util.encode(input.getPassword()));
            newUser.setPhone(input.getPhone());
            newUser.setCreateTime(LocalDateTime.now());
            newUser.setRoles(input.getRoles());
            newUser.insertOrUpdate();
            return newUser;
        }

    }

    @Override
    public void deleteUser(String id) {
        getBaseMapper().deleteById(id);
    }

    @Override
    public User detail(String id) {
        return getBaseMapper().selectById(id);
    }

    @Override
    public User findByPhoneName(String phoneName) {
        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, phoneName));
        return user;
    }
}
