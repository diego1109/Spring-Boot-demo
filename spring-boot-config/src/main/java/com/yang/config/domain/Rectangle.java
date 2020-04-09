package com.yang.config.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
