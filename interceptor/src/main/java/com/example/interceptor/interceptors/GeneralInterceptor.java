package com.example.interceptor.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建拦截器的方式：
 * 1. 创建一个类，并且实现 HandlerInterceptor 接口。
 * 2. 根据业务需求，重写接口中的方法。
 */

@Component
public class GeneralInterceptor implements HandlerInterceptor {

/**
 * @description :  测试 pre interceptor。
 * @author : diego
 * @date : 2020/11/18 20:06
 * @param request :
 * @param response :
 * @param handler :
 * @return : boolean
 */

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("--- proHandler ---");
    return true;
  }


  // 测试 post interceptor。
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    System.out.println("--- postHandler ---");
  }
}
