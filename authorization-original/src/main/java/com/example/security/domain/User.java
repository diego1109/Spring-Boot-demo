package com.example.security.domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.ognl.ASTList;

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

  public List<String> getRoles() {
    return new ArrayList<String>() {{
      add("ADMIN");
    }};
  }
}
