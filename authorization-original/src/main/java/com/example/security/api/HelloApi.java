package com.example.security.api;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloApi {

  @GetMapping("/hello/forward")
  public void helloForward(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("--- here is helloForward method ---");
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/forward?method=forward");
    requestDispatcher.forward(request,response);
  }

  @GetMapping("/hello/redirect")
  public void helloRedirect(HttpServletRequest request,HttpServletResponse response)
      throws IOException {
    System.out.println("--- here is helloRedirect method ---");
    response.sendRedirect(request.getContextPath() + "/forward?method=redirect");
  }

  @GetMapping("/forward")
  public String filterForward(HttpServletRequest request,HttpServletResponse response){
    String method = request.getParameter("method");
    if (method == null){
      System.out.println("--- request straight to forward --- ");
      return "request straight to forward !";
    }
    System.out.println("hello "+ method);
    return "hello "+ method ;
  }

}
