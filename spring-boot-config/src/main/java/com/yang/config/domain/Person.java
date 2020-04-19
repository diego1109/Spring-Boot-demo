package com.yang.config.domain;


import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  将配置文件中配置的每个组件值，映射到这个组件中。
 * @ConfigurationProperties 告诉Spring boot将这个类中的所有属性和配置文件中的属性进行绑定。
 * prefix = "person" 将Person类中的属性和yaml配置文件中person中的属性一一映射。
 *
 * 只有在Spring 容器中的组件在能使用容器提供的ConfigurationProperties功能。所以必须给Person类加上@Component注解。
 */


@Getter
@ToString
@Component
@Setter
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;

}
