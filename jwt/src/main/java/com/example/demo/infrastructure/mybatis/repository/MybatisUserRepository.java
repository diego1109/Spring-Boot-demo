package com.example.demo.infrastructure.mybatis.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.infrastructure.mybatis.mapper.UserMapper;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisUserRepository implements UserRepository {

  @Autowired
  private UserMapper userMapper;

  @Override
  public void save(User user) {
    userMapper.save(user);
  }

  @Override
  public Optional<User> findByUser(String username, String password) {
    return Optional.ofNullable(userMapper.findUser(username,password));
  }
}
