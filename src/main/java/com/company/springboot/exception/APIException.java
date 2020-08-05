package com.company.springboot.exception;

import lombok.Getter;

/**
 * @author Min Xu
 * @description Customized exception
 */

@Getter
public class APIException extends RuntimeException {
  private int code;
  private String msg;

  public APIException() {
    this(1001, "API error");
  }

  public APIException(String msg) {
    this(1001, msg);
  }

  public APIException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }
}
