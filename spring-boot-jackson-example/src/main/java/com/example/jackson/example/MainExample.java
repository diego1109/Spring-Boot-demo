package com.example.jackson.example;

import com.example.jackson.domain.Circle;
import com.example.jackson.domain.Rectangle;
import com.example.jackson.domain.Shape;
import com.example.jackson.domain.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

public class MainExample {

  public static void main(String[] args) throws Exception {
    View view = new View();
    view.setShapes(
        new ArrayList<Shape>() {
          {
            add(Rectangle.of(3, 6));
            add(Circle.of(5));
          }
        });

    System.out.println("---- serializing ---");
    ObjectMapper om = new ObjectMapper();
    String s = om.writeValueAsString(view);
    System.out.println(s);

    System.out.println("--- deserializing ---");
    View view1 = om.readValue(s,View.class);
    System.out.println(view1);

  }

}
