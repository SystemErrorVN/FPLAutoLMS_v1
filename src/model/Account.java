/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class Account {
    private Server server;
    private String cookie;
    private String name;
    private String id;
    private String birthDay;
    private String sex;
    private String email;

    public Account() {
    }

    
    public Account(Server server, String cookie, String name, String id, String birthDay, String sex, String email) {
        this.server = server;
        this.cookie = cookie;
        this.name = name;
        this.id = id;
        this.birthDay = birthDay;
        this.sex = sex;
        this.email = email;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }


    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "cookie=" + cookie + ", name=" + name + ", id=" + id + ", birthDay=" + birthDay + ", sex=" + sex + ", email=" + email + '}';
    }
    
}
