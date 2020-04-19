package com.yang.config.domain;


import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@ToString
public class InjectWithoutConfigurationFile {

  // 注入普通字符串
  @Value("diego")
  private String name;

  // 注入表达式
  @Value("#{T(java.lang.Math).random()}")
  private double random;

  // 注入系统属性
  @Value("#{systemProperties['os.name']}")
  private String defaultLocale;

  // 注入其他bean的属性值
  @Value("#{rectangle.length}")
  private int rectangleLength;

  // 注入url
  @Value("http://www.baidu.com")
  private Resource baiduUrls;

}
