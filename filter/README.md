### 创建过滤器要做的四件事情
1. 创建类 Filter接口。
2. 指定过滤器要拦截的请求。
3. 指定过滤器的拦截顺序。
4. 注册过滤器。

### 创建过滤器的方式
#### "注册"的方式 （采用配置 Bean 方式）
```java
public class CustomFilter1 implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException { }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() { }
}
```
```java
@Configuration
public class ServletConfig {

  @Bean
  public FilterRegistrationBean<CustomFilter1> registerCustomFilter1(){
    FilterRegistrationBean customServlet1Bean = new FilterRegistrationBean<>();
    customServlet1Bean.setFilter(new CustomFilter1());
    customServlet1Bean.setName("customFilter1"); // 声明该过滤器的名字（可以不要）。
    customServlet1Bean.addUrlPatterns("/forward"); // 声明要拦截的请求。（只拦截"/forward" 的请求）
    customServlet1Bean.setOrder(1); // 声明该过滤器的拦截顺序。（第一个上手拦截）
    return customServlet1Bean;
  }
}
```
#### "扫描"的方式 （采用注解方式）
```java
@Order(2)
@WebFilter(filterName = "customFilter2", urlPatterns = "/forward")
public class CustomFilter2 implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException { }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() { }
}
```
```java
@SpringBootApplication
@ServletComponentScan
public class FilterApplication {

  public static void main(String[] args) {
    SpringApplication.run(FilterApplication.class, args);
  }

}
```

### chain.doFilter(request, response) 做了什么事情？
在每个过滤器的 `doFilter(*****)` 方法中都会看到调用`chain.doFilter(request, response)` 方法，这是干什么呢？

前面提到过，实际使用中，过滤器按顺序被“线”串起来，并且“线”末尾连接一个 servlet。`chain.doFilter(request, response)`  就是将请求交给下一个过滤器的 `doFilter(*****)`，如果当前过滤器是最后一个过滤器，那么 `chain.doFilter(request, response)` 就会将请求交给 servlet。 比如这里的顺序是：`CustomFilter1 ->  CustomFilter2 -> servlet` 在 CustomFilter1 的 `doFilter(*****)` 中执行 `chain.doFilter` ，请求就会到 CustomFilter2 的  `doFilter(*****)`  方法里面，再执行 `chain.doFilter` 请求 就会到 servlet 中去。那 servlet 的 response 会按照：`CustomFilter1 <-  CustomFilter2 <- servlet` 的顺序”穿出去“。
其实"穿进来"、"穿出去"是 入栈和出栈。

### 请求转发和重定向

请求转发不会触发过滤器。
重定向会触发过滤器。

[博客](https://blog.csdn.net/yy_diego/article/details/110447507)