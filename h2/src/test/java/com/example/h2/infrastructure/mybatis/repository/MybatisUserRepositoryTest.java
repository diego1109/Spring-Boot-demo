package com.example.h2.infrastructure.mybatis.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.example.h2.domain.User;
import com.example.h2.domain.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@MybatisTest
@Import(MybatisUserRepository.class)
class MybatisUserRepositoryTest {


  @Autowired
  private UserRepository userRepository;

  @Test
  public void should_get_user_succeed(){
    Optional<User> optional = userRepository.ofId("diego");
    assertThat(optional.isPresent(),is(true));

    User fetched = optional.get();
    assertThat(fetched.getName(),is("diego"));
    assertThat(fetched.getPassword(),is("123"));
  }

}