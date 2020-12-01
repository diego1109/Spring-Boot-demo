package com.example.filter.configs;

import com.example.filter.myfilters.CustomFilter1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

  @Bean
  public FilterRegistrationBean<CustomFilter1> registerCustomFilter1(){
    FilterRegistrationBean customServlet1Bean = new FilterRegistrationBean<>();
    customServlet1Bean.setFilter(new CustomFilter1());
    customServlet1Bean.setName("customFilter1");
    customServlet1Bean.addUrlPatterns("/forward");
    customServlet1Bean.setOrder(1);
    return customServlet1Bean;
  }
}
