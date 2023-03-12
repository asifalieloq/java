/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutorialspoint.test;

/**
 *
 * @author Asif Ali
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean(name = "userLogin", eager = true)
@RequestScoped
public class UserLogin {
    private String message ="Enter username and password.";
    private String username;
    private String password;
    private String logInResult;
    public String login(){
    	if("user1".equalsIgnoreCase(username) && "user1".equalsIgnoreCase(password)) {
    		message ="Successfully logged-in.";
    		return "home";
    	} else {
    		message ="Wrong credentials.";
    		return "index";
    	}
    }
     public String moveToPage1() {
       return logInResult;
   }
    public String getMessage() {
	return message;
    }
    public void setMessage(String message) {
	this.message = message;
    }
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

    public void setLogInResult(String logInResult) {
        this.logInResult = logInResult;
    }

    public String getLogInResult() {
        return logInResult;
    }

    
    
} 
