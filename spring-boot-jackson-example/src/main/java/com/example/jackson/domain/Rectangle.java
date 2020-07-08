package com.example.jackson.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Rectangle extends Shape {
  private int w;
  private int h;

  private String type = "REC";

  public static Rectangle of(int w, int h) {
    Rectangle rectangle = new Rectangle();
    rectangle.setH(h);
    rectangle.setW(w);
    return rectangle;
  }
}
