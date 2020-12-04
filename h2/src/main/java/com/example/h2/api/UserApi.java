package com.example.h2.api;


import com.example.h2.domain.UserRepository;
import java.util.Optional;
import com.example.h2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {

  private UserRepository userRepository;

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

}

