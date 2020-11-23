package com.example.demo.Interceptors;

import com.example.demo.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    System.out.println("--- here ---");

//    String token = request.getHeader("token");
    String token = request.getHeader("token");

    System.out.println("token: "+ token);
    try {
      JwtUtil.verify(token);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
