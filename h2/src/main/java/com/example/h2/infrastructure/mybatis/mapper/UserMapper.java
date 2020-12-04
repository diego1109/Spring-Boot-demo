package com.example.h2.infrastructure.mybatis.mapper;

import com.example.h2.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

  User ofId(@Param("userId") String userId);

}
