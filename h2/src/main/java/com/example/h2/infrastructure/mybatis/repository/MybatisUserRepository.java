package com.example.h2.infrastructure.mybatis.repository;

import com.example.h2.domain.User;
import com.example.h2.domain.UserRepository;
import com.example.h2.infrastructure.mybatis.mapper.UserMapper;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisUserRepository implements UserRepository {

  private UserMapper userMapper;

  @Autowired
  public MybatisUserRepository(
      UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public Optional<User> ofId(String userId) {
    return Optional.ofNullable(userMapper.ofId(userId));
  }
}
