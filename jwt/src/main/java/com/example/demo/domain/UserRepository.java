package com.example.demo.domain;

import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> findByUser(String username,String password);
}
