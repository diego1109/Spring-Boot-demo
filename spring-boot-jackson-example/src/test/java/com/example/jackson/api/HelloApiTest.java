package com.example.jackson.api;

import org.junit.Test;

public class HelloApiTest {

  @Test
  public void hello(){
    HelloApi helloApi = new HelloApi();
    System.out.println(helloApi.hello());
  }
}