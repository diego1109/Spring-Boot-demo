package com.example.security.infrastructure.mybatis.mapper;

import com.example.security.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

  User ofId(@Param("userId") String userId);

}

