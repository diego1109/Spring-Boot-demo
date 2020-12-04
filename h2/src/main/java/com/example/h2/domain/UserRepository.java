package com.example.h2.domain;

import java.util.Optional;

public interface UserRepository {

  Optional<User> ofId(String userId);
}
