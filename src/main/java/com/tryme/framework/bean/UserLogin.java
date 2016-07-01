package com.tryme.framework.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.data.mongodb.core.mapping.Field;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserLogin {

 @Field
 private String password;
 
 @Field
 private String username;
 
 public UserLogin() {
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }
 
 
}