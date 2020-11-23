package com.example.demo.api;


import com.example.demo.api.exception.InvalidRequestException;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.utils.JwtUtil;
import java.util.HashMap;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserApi {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity auth(@RequestBody @Validated LoginParameter parameter,
      BindingResult bindingResult) {

    log.info("username: {}", parameter.getUsername());
    log.info("password: {}", parameter.getPassword());

    if (bindingResult.hasErrors()) {
      throw new InvalidRequestException("", bindingResult);
    }

    Optional<User> fetch = userRepository
        .findByUser(parameter.getUsername(), parameter.getPassword());
    if (fetch.isPresent()) {
      return ResponseEntity.status(200).body(new HashMap<String, String>() {{
        put("username", parameter.getUsername());
        put("token", JwtUtil.getToken(new HashMap<String, String>() {{
          put("id", fetch.get().getId());
          put("username", parameter.getUsername());
        }}));
      }});
    } else {
      throw new InvalidRequestException("Invalid username or password");
    }
  }

  @GetMapping
  public ResponseEntity getUser() {
    System.out.println("--- here  get  user ---");
    return ResponseEntity.status(200).body(new HashMap<String, Object>() {{
      put("status", "get succeed");
    }});
  }

}

@Getter
@NoArgsConstructor
class LoginParameter {

  @NonNull
  private String username;
  @NonNull
  private String password;
}
