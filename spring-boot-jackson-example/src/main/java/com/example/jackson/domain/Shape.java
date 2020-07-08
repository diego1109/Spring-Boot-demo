package com.example.jackson.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "type" ,visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = Rectangle.class, name = "REC"),
  @JsonSubTypes.Type(value = Circle.class, name = "CIR")
})
public abstract class Shape {}
