package com.dzl.blog2.dto.user;

import lombok.Data;

@Data
public class UserInput {
    private String phone;
    private String username;
    private String password;
    private String avatar;
}
