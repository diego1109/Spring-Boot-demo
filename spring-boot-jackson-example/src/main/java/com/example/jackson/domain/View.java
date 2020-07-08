package com.example.jackson.domain;

import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class View {
  private List<Shape> shapes;
}
