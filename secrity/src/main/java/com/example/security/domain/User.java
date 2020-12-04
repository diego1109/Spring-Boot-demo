package com.example.security.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class User {

  String name;
  String password;


  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }
}
