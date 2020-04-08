package com.yang.config;

import com.yang.config.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigApplicationTests {

  @Autowired
  private Person person;

  @Test
  void contextLoads() {
    System.out.println(person);
  }

}
