package com.company.springboot.exception;

/**
 * @author Min Xu
 * @description Bad request exception
 */

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1658974017960059039L;

  public BadRequestException(String message) {
    super(message);
  }

}
