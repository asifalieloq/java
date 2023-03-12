/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main_pkg;

import java.io.Serializable;

/**
 *
 * @author Asif Ali
 */
public class MydbSetting implements Serializable{
    String url="";
    String username="";
    String password="";

    public MydbSetting() {
    }

    
    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
