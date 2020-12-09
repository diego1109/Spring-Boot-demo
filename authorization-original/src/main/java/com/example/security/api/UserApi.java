package com.example.security.api;

import com.example.security.domain.User;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {

  private UserRepository userRepository;
  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  public UserApi(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/{userId}")
  public ResponseEntity getEmployee(@PathVariable("userId") String UserId){
    Optional<User> optional = userRepository.ofId(UserId);
    if (!optional.isPresent()){
      System.out.println("--- "+ UserId + " not found ---");
    }

    return ResponseEntity.ok(optional.get());
  }

  @GetMapping
  public ResponseEntity getCurrentUser(@AuthenticationPrincipal Object current){
    System.out.println("current user : "+ current);

    getSecurityContextInfo();

    getHttpSessionInfo();

    return ResponseEntity.ok(current);
  }

  private void getHttpSessionInfo() {
    HttpSession session = request.getSession(false);
    if (session == null){
      System.out.println("--- session is null ---");
      return;
    }
  }

  private void getSecurityContextInfo() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    String username = authentication.getName();
    Object principal = authentication.getPrincipal();
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
  }

}
