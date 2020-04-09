# 配置文件的注入

配置文件中的属性可以通过以下几个注解注入：
- `@ConfigurationProperties`
- `@Value`
- `@PropertiesSource`

## 1、application.yml
### 1.1 基本语法

K:(空格)V：表示一堆键值对（空格必须有）。

以**空格**的缩进来控制层级关系，只要是左对齐的一列数据，都是同一个层级的。

```yml
server:
	port: 8080
	path: /hello
```

### 1.2、值的写法

#### （1）字面量：普通的值（数字、字符串、布尔）

​	K:V：字面直接来写；

​	字符串默认不用加上单引号或者双引号；

#### （2）对象（属性和值）就是键值对：

    ​K:V：在下一行来写对象的属性和值的关系，注意缩进就可以了。
    
​   象还是k:v方式

一般写法：

```yaml
friends:
	lastName: zhangsan
	age:	20
```

行内写法：

```yaml
friends: {lastName: zhangsan,age:	18}
```

#### 数组（List、Set）

用 - 值表示数组中的一个元素

```yaml
pets:
	- cat
	- dog
	- pig
```

行内写法：

```yaml
pets: [cat,dog,pig]
```
## 2、实验

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

- **@ConfigurationProperties** 注解到哪个类上，就表示这个类的bean的值是从配置文件中获取的。
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
