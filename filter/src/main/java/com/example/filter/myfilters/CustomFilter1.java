package com.example.filter.myfilters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// 采用配置 Bean 方式创建过滤器。
public class CustomFilter1 implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // 服务启动时，加载一次过滤器。
    System.out.println("--- custom filter 1 init ---");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    System.out.println(" custom filter 1 before  ...... ");
    chain.doFilter(request, response);
    // servlet 的 response 会来到这里。
    System.out.println(" custom filter 1 after  ...... ");

  }

  @Override
  public void destroy() {
    // 服务器变关闭时，销毁过滤器。
    System.out.println("--- custom filter 1 destroy ---");
  }
}
