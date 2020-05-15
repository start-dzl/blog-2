package com.dzl.blog2.config.security;


import com.dzl.blog2.entity.User;
import com.dzl.blog2.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 用户信息认真服务
 *
 * @author yu
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    @Autowired
    private IUserService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.debug("load a admin whose PhoneName:{}", s);
        User phone = adminService.findByPhoneName(s);
        if (phone == null) {
            throw new UsernameNotFoundException("Can't load the user: " + s);
        }
        return phone;
    }
}