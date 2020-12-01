package com.example.filter.api;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloFilter {

  // 这种方法是不 work 的
  @GetMapping("/hello")
  public String helloFilter() {
    String hello = "hello filter \n";
    return "forward:/forward";
  }

  @GetMapping("/hello/forward")
  public void helloForward(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/forward?method=forward");
    requestDispatcher.forward(request,response);
  }

  @GetMapping("hello/redirect")
  public void helloRedirect(HttpServletRequest request,HttpServletResponse response)
      throws IOException {
    response.sendRedirect(request.getContextPath() + "/forward?method=redirect");
  }

  @GetMapping("/forward")
  public String filterForward(HttpServletRequest request,HttpServletResponse response){
    String method = request.getParameter("method");
    System.out.println("method: "+ method);
    return "hello "+ method ;
  }

}
