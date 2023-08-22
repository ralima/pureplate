package com.pureplate.exception;

public class FoodProductNotFoundException extends RuntimeException{

  public FoodProductNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
