package com.example.filter.myfilters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;

@Order(2)
@WebFilter(filterName = "customFilter2", urlPatterns = "/forward")
public class CustomFilter2 implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("--- custom filter 2 init ---");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    System.out.println(" custom filter 2 before  ...... ");
    chain.doFilter(request, response);
    System.out.println(" custom filter 2 after  ...... ");

  }

  @Override
  public void destroy() {
    System.out.println("--- custom filter 2 destroy ---");
  }
}
