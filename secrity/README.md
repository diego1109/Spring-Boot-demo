## 功能
为了测试 spring security 的一些小点。

## 零星小点
#### 1、当添加依赖后，默认会启动安全保护，所有的 api 只有通过认证后才能访问。
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
    testImplementation 'org.springframework.security:spring-security-test'
}
```
web 服务启动后，系统会自动创建一个用户，该用户可以访问所有 api。

username：user
password：web 服务自动生成。
```java
Using generated security password: b6b18462-3980-4920-a893-e87993facc36
```
#### 2、`@EnableWebSecurity`注解加不加都没关系，因为安全防护默认会启动。
```java
@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter{


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/hello/*").hasRole("ADMIN")
        .antMatchers("/forward").hasAnyRole("USER","ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic();
  }
}

```


#### 4、用户怎么认证？

`protected void configure(HttpSecurity http) throws Exception` 方法，配置了 api 的访问权限，哪些需要认证、哪些不需要认证。也配置了认证的方式，用户可以采用表单或者 HTTP 的方式进行认证。

那请问？web 服务怎么执行认证呢？ 总不能随便输个用户名和密码就让他通过认证吧。

按照常规思路，应该是当前用户输入的用户名和密码跟 web服务 已存的用户名密码 比对下，如果一样，那就通过认证了。却是是这个思路，接下来就是这个思路到底是怎么实现的呢？

##### 4.1 基于内存的认证
就是把已有用户放在内存中，与外部的用户名密码比对。这种方式适合测试环境使用，测一测功能是否正常。
```java
//  这里在内存中创建了两个用户：user 和 admin。

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.inMemoryAuthentication()
      .withUser("user").password("{noop}password").roles("USER")
      .and()
      .withUser("admin").password("{noop}password").roles("USER","ADMIN");
}
```
或者：
```java
@Bean
public UserDetailsService userDetailsService() {
    User.UserBuilder users = User.builder();
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(users.username("user").password("{noop}password").roles("USER").build());
    manager.createUser(users.username("admin").password("{noop}password").roles("USER", "ADMIN").build());
    return manager;
}
```

##### 4.2 基于数据库的认证
这种方式是读取数据库中的用户，与输入的用户名和密码对比，被用在生产环境中。
```java

public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
  
  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.
        userDetailsService(customUserDetailsService)
       .passwordEncoder(new BCryptPasswordEncoder());
  }
}

```
```java
@Component
class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optional = userRepository.ofId(username);
    if (!optional.isPresent()){
      throw new UsernameNotFoundException(username + " not found!");
    }
    User fetched = optional.get();
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return  new org.springframework.security.core.userdetails.User(fetched.getName(),passwordEncoder.encode(fetched.getPassword()),
        AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
  }
}
```



333、请求转发和重定向。

请求从第一个 servlet 转发到第二个 servlet 时，不再需要安全校验。

请求从第一个 servlet 重定向到第二个 servlet时，仍旧需要安全校验。

原因依旧是，转发是一次请求，重定向是两次请求。再精确些就是 转发时在 servlet 后面发生的，重定向是从客户端发来一个新的请求。