package com.example.demo.infrastructure.mybatis.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.example.demo.domain.User;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(MybatisUserRepository.class)
class MybatisUserRepositoryTest {

  @Autowired private MybatisUserRepository userRepository;

  @Test
  public void should_save_user_succeed(){
    User user = new User(UUID.randomUUID().toString(),"amos","password");

    userRepository.save(user);

    Optional<User> fetch = userRepository.findByUser(user.getName(),user.getPassword());
    assertThat(fetch.isPresent(),is(true));
    assertThat(fetch.get().getId(),is(user.getId()));
    assertThat(fetch.get().getName(),is(user.getName()));
    assertThat(fetch.get().getPassword(),is(user.getPassword()));
  }
}