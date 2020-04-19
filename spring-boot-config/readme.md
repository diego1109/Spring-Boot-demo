# 配置文件中属性的注入

在Spring boot项目中，一般在全局配置文件配置属性值，之后注入到java类中对应的属性中。
全局配置文件：
- application.properties
- application.yaml

这两个都是全局配置文件，但Properties的优先级高于yaml，Spring Boot项目先在properties中找属性，如果找到，则不再访问yaml。



在 `application.yaml` 中：
1. 通过配置将服务器端口号改成8081；
2. 在yaml中配置一个person对象，并且将该person的值用`@ConfigurationProperties`注入到java `Person`类中去。
3. 在yaml中配置一个对象rectangle，并且将该rectangle的值用`@Value`注入到java `Rectangle`类中。

yml中的配置：
```$yml
server:
  port: 8081

person:
  lastName: yang
  age: 22
  boss: true
  birth: 2020/09/21
  maps:
    k1: value1
    k2: value2
  lists:
    - one
    - two
    - three
  dog:
    name: dogName
    age: 1
```

java类：
```java
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
    private Dog dog;

}

```

- **@ConfigurationProperties** 它的作用是从配置文件中绑定属性到对应的bean上。
- **prefix = "person"** 将Person类中的属性和yaml配置文件中person中的属性一一映射。
- 只有Spring 容器中的bean才能使用ConfigurationProperties功能，所以必须给 `Person` 类加上`@Component` 注解。
- 这个 ConfigurationProperties 的实现过程应该是：Person先通过无参构造函数创建了 person 对象，person对象再使
用`setter`方法将配置中的值赋给对应的属性。**因为在测试中如果没有`setter`方法，就会报错：不能将配置属性值通过setter
方法绑定到Person类中对应的属性上**




yaml中的rectangle：
```yaml
rectangle:
  length: 20
  width: 30
```
java中的Rectangle：
```java
@Component
@Getter
@ToString
public class Rectangle {

  private int length;

  private int width;

  public Rectangle(
      @Value("${rectangle.length}") String length, @Value("${rectangle.width}") String width) {
    this.length = Integer.parseInt(length);
    this.width = Integer.parseInt(width);
  }

}
```
