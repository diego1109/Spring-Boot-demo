package com.example.demo.api.exception;

import org.springframework.validation.Errors;


public class InvalidRequestException extends RuntimeException {

  private final Errors errors;

  public InvalidRequestException(String message, Errors errors) {
    super(message);
    this.errors = errors;
  }

  public InvalidRequestException(String message){
    this(message,null);
  }
}
