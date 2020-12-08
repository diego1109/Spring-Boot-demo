package com.example.security.api;

import com.example.security.domain.User;
import java.util.Optional;

public interface UserRepository {

  Optional<User> ofId(String userId);
 }
