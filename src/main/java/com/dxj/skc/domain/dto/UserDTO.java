package com.dxj.skc.domain.dto;

import java.io.Serializable;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/11/18 9:55
 * @CopyRight: 2020 skc all rights reserved.
 */
public class UserDTO implements Serializable {

    private String username;
    private String password;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
