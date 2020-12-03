`OncePerRequestFilter` 实现了 `Filter` 接口，但是两者有什么区别呢？

### 在使用上方法上：
`MyFilter implement Filter` 的类，要重写 `doFilter(****)` 方法。将所有的业务逻辑都写到里面去。

`OncePerRequestFilter` 实现了 Filter 并且已经重写了 `doFilter(****)`，
```java
@Override
public final void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
      // 好几个 if...else if... else 的逻辑判断，这里就省略了。
      
      //最后调用了这个方法。
      doFilterInternal(httpRequest, httpResponse, filterChain);
		
}

//  这是个抽象方法。
protected abstract void doFilterInternal(
HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException;
```

所以 `MyFilter implement OncePerRequestFilter` 只需要重写`doFilterInternal(****)`, 将过滤的业务逻辑写到这里面就可以了。

### 在目的上
`Filter` 已经可以实现请求过滤了，那为何还要有 `OncePerRequestFilter`？后者比前者先进在哪了，或者有什么特殊之处？

理论上说：
```text

- 请求发向 servlet 时会被 Filter 拦截，如果 servlet 将请求转发给另一个 servlet，请求发向第二个 servlet 时，依旧会被相同的 Filter 拦截。结果就是一个请求被同一个 Filter 拦截了两次。

- `OncePerRequestFilter` 一个请求只被过滤器拦截一次。请求转发不会第二次触发过滤器。
```

实际测试结果：
```
`MyFilter implement Filter` 请求转发不会二次触发过滤器，重定向会触发过滤器。
`MyFilter implement OncePerRequestFilter` 请求转发不会二次触发过滤器，重定向会触发过滤器。

```
我没有测出来这个效果，也许是因为还没有理清楚 servlet、controller、controller里面的方法 这三者之间的关系。

