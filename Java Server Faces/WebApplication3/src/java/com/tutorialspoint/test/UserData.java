/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutorialspoint.test;

/**
 *
 * @author Asif Ali
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {
   private static final long serialVersionUID = 1L;
   private String name;
   private String password;
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getPassword() {
      return password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }	
   
   public String login() {
      return "result";
   }	
}