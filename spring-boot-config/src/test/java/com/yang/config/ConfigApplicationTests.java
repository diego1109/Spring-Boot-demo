package com.yang.config;

import com.yang.config.domain.ConfigProperties;
import com.yang.config.domain.CustomConfigProperties;
import com.yang.config.domain.Dog;
import com.yang.config.domain.InjectWithoutConfigurationFile;
import com.yang.config.domain.Person;
import com.yang.config.domain.PersonConfig;
import com.yang.config.domain.Properties;
import com.yang.config.domain.Rectangle;
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

  @Autowired
  private Rectangle rectangle;

  @Autowired
  private Dog dog;

  @Autowired
  private Properties properties;

//  @Autowired
//  private Pets pets;

  @Autowired
  private InjectWithoutConfigurationFile injectWithoutConfigurationFile;

//  @Test
//  void loadPets(){
//    System.out.println(pets);
//  }

  @Autowired
  private ConfigProperties configProperties;

  @Autowired
  private CustomConfigProperties customConfigProperties;

  @Autowired
  private PersonConfig personConfig;

  @Test
  void loadPersonConfig(){
    System.out.println(personConfig);
  }

  @Test
  void loadCustomConfigProperties(){
    System.out.println(customConfigProperties);
  }

  @Test
  void loadConfigProperties(){
    System.out.println(configProperties);
  }

  @Test
  void loadInjectWithoutConfigurationFile(){
    System.out.println(injectWithoutConfigurationFile);
  }

  @Test
  void loadProperties(){
    System.out.println(properties);
  }

  @Test
  void loadDog(){
    System.out.println(dog);
  }

  @Test
  void contextLoads() {
    System.out.println(person);
  }


  @Test
  void contextLoadsRectangle() {
    System.out.println(rectangle);
  }

}
