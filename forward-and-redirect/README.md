### 功能：
```txt
测试请求转发和请求重定向。
```

### 依赖：
```groovy
compile group: 'javax.servlet', name: 'javax.servlet-api', version: '4.0.1'
```

### NOTE：
转发（重定向）目的地：
```java
  @GetMapping("/forward")
  public String filterForward(HttpServletRequest request,HttpServletResponse response){
    String method = request.getParameter("method");
    System.out.println("method: "+ method);
    return "hello "+ method ;
  }
```
请求转发：
```java
  @GetMapping("/hello/forward")
  public void helloForward(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/forward?method=forward");
    requestDispatcher.forward(request,response);
  }
```

重定向：
```java
  @GetMapping("hello/redirect")
  public void helloRedirect(HttpServletRequest request,HttpServletResponse response)
      throws IOException {
    response.sendRedirect(request.getContextPath() + "/forward?method=redirect");
  }
```

### 测试 URL
转发：
```java
http://localhost:8080/hello/forward
```

重定向：
```java
http://localhost:8080/hello/redirect
```