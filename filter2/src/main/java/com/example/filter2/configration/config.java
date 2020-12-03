package com.example.filter2.configration;

import com.example.filter2.filters.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

  @Bean
  public FilterRegistrationBean<MyFilter> registerMyServlet(){
    FilterRegistrationBean filterBean = new FilterRegistrationBean();
    filterBean.setFilter(new MyFilter());
    filterBean.setOrder(1);
    filterBean.addUrlPatterns("/*");
    filterBean.setName("myFilter");
    return filterBean;
  }
}
