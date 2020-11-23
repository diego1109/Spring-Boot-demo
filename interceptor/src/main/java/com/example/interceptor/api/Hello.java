package com.example.interceptor.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello  {

  @GetMapping("/springboot")
  public String helloSpringBoot(){
    System.out.println("--- hello spring boot ---");
    return "hello Spring Boot \n";
  }

  @GetMapping("/world")
  public String helloWorld(){
    System.out.println("--- hello world ---");
    return "hello world \n";
  }

}
