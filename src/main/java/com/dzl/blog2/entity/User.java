package com.dzl.blog2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.dzl.blog2.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2020-04-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 姓名
     */
    private String name;

    private String password;

    private LocalDateTime createTime;

    private String avatar;

    private String phone;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Role[] roles;

  /*  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>();
        for (Role role : roles) {
            set.add(new SimpleGrantedAuthority(role.name()));
        }
        return set;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/
}
