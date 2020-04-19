package com.yang.config.domain;


import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "personconfig")
@ToString
@Setter
public class PersonConfig {
  private String name;
  private int age;
}
