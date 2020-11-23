package com.example.demo.infrastructure.mybatis.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

  void save(@Param("user") User user);

  User findUser(@Param("username") String username, @Param("password") String password);
}
