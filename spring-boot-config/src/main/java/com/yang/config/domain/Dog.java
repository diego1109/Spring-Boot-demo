package com.yang.config.domain;


import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Value注解注入单个String类型属性。
 */

@Getter
@ToString
@Component
public class Dog {
    @Value("${dog.name}")
    private String name;
    @Value("${dog.color}")
    private String[]  color;
}
