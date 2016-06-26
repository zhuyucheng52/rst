package com.echo.rst.user;

import com.echo.rst.entity.CommonEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by echo on 16-6-11.
 * 用户
 */
@Entity
@Table(name = "tbl_user")
public class User extends CommonEntity implements Serializable {
    private static final long serialVersionUID = 681085188268786291L;

    /** 用户姓名 */
    private String userName;
    /** 登录名 */
    private String loginName;
    /** 密码 */
    private String password;
    /** 描述 */
    private String description;

    public User() {
    }

    public User(String userName, String loginName, String password, String description) {
        this.userName = userName;
        this.loginName = loginName;
        this.password = password;
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
