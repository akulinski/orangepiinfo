package com.albert.orangepiinfo.requestresponsemodel;

import lombok.Getter;
import lombok.Setter;

public class LoginModel {
    @Getter @Setter
    private int id=0;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    public LoginModel(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public LoginModel() { }

    @Override
    public String toString() {
        return "LoginModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
