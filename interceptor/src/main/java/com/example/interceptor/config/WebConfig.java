package com.example.interceptor.config;

import com.example.interceptor.interceptors.GeneralInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义了拦截器，还必须将该拦截器告诉 spring 才能使用，即注册拦截器。
 * 注册拦截器的方式：
 * 方式一：在 spring mvc 中，需要在 .xml 文件中声明拦截器，tomcat 读取 xml 文件时就完成了拦截器注册。
 * 方式二：在 spring boot 中，采用 java config 的方式注册拦截器。
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new GeneralInterceptor())
        .addPathPatterns("/hello/world")
        .excludePathPatterns("/hello/springboot");
  }

}
