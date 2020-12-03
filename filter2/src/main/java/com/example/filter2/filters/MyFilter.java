package com.example.filter2.filters;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class MyFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    System.out.println("--- before servlet get parameterMap --- ");
    Map<String, String[]> requestParameterMap = request.getParameterMap();
    for(String key : requestParameterMap.keySet()){
      System.out.println("Key : "+ key +", Value: "+ requestParameterMap.get(key)[0]);
    }
    filterChain.doFilter(request,response);
    System.out.println("--- after servlet --- ");

  }
}
