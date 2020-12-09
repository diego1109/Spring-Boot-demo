package com.example.security.configration;


class WebSecurityConfig{

}

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Autowired
//  private  AuthorizationUserFilter authorizationUserFilter;
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//        .authorizeRequests()
//        .antMatchers("/hello/*").hasRole("ADMIN")
//        .antMatchers("/forward").hasAnyRole("USER", "ADMIN")
//        .anyRequest().authenticated()
//        .and()
//        .formLogin()
//        .and()
//        .httpBasic();
//
//    http.addFilterAfter(authorizationUserFilter, UsernamePasswordAuthenticationFilter.class);
//
//  }
//
//  @Autowired
//  private CustomUserDetailsService customUserDetailsService;
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.
//        userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//  }
//
//}
//
//
//@Component
//class CustomUserDetailsService implements UserDetailsService {
//
//  @Autowired
//  private UserRepository userRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<User> optional = userRepository.ofId(username);
//    if (!optional.isPresent()) {
//      throw new UsernameNotFoundException(username + " not found!");
//    }
//    User fetched = optional.get();
//    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    String password = passwordEncoder.encode(fetched.getPassword());
//
//    return new org.springframework.security.core.userdetails.User(fetched.getName(), password,
//        fetched.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//  }
//
//}
