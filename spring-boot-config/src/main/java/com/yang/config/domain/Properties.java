package com.yang.config.domain;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ToString
@Component
public class Properties {

  @Value("${value.from.file}")
  private String valueFromFile;

  @Value("${priority}")
  private String prioritySystemProperty;

  @Value("${listOfValues}")
  private String[] valuesArray;
}
