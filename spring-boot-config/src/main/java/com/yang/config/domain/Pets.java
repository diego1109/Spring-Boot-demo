package com.yang.config.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Value 注解注入String数组类型数组
 *
 * yaml注解String数组 不能成功。
 */

@Getter
@ToString
public class Pets {
  @Value("${kinds}")
  private String[] kinds;
}
