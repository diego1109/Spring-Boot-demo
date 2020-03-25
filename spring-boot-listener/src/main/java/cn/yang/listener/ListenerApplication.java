package cn.yang.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ListenerApplication {

  public static void main(String[] args) {

    SpringApplication.run(ListenerApplication.class, args);

//    System.out.println("--- hello listener ---");
  }

}
