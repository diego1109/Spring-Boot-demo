package com.yang.config.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class Dog {
    private String name;
    private Integer age;
}
