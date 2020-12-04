package com.example.security.configration;


import com.example.security.api.UserRepository;
import com.example.security.domain.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//  所有的 api 都放开。
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//        .authorizeRequests().anyRequest().permitAll();
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/hello/*").hasRole("ADMIN")
        .antMatchers("/forward").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic();
  }

  // 基于内存的用户认证。
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .withUser("user").password("{noop}password").roles("USER")
//        .and()
//        .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
//  }

  //  @Bean
//  public UserDetailsService userDetailsService() {
//    User.UserBuilder users = User.builder();
//    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//    manager.createUser(users.username("user").password("{noop}password").roles("USER").build());
//    manager.createUser(users.username("admin").password("{noop}password").roles("USER", "ADMIN").build());
//    return manager;
//  }

//  基于数据库的用户认证。
  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.
        userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }
}


@Component
class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    System.out.println("--- username: "+ username);
    Optional<User> optional = userRepository.ofId(username);
    if (!optional.isPresent()) {
      throw new UsernameNotFoundException(username + " not found!");
    }
    User fetched = optional.get();
//    System.out.println("--- user: "+ fetched);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String password = passwordEncoder.encode(fetched.getPassword());
    return new org.springframework.security.core.userdetails.User(fetched.getName(), password,
        AuthorityUtils.commaSeparatedStringToAuthorityList("MEMBER"));
  }
}
