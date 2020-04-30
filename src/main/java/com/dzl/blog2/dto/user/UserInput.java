package com.dzl.blog2.dto.user;

import com.dzl.blog2.enums.Role;
import lombok.Data;


@Data
public class UserInput {
    private String phone;

    private String username;

    private String password;

    private String avatar;

    private Role[] roles;
}
