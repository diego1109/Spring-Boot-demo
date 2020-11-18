package com.example.demo.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookies")
public class Cookies {

  @GetMapping
  public String createCookie(HttpServletRequest request, HttpServletResponse response){

    Cookie cookie = new Cookie("company", "openbayes");
    cookie.setPath("/cookies");
//    cookie.setMaxAge(60*60*24*10);
    response.addCookie(cookie);

    return "Cookies generated \n";
  }
}
