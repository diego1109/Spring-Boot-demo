package com.example.jackson.domain;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Circle extends Shape {
  int radius;
  private String type = "CIR";

  public static Circle of(int radius){
    Circle circle = new Circle();
    circle.setRadius(radius);
    return circle;
  }

}
