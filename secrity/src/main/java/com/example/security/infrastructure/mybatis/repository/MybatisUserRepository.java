package com.example.security.infrastructure.mybatis.repository;

import com.example.security.api.UserRepository;
import com.example.security.domain.User;
import com.example.security.infrastructure.mybatis.mapper.UserMapper;
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
