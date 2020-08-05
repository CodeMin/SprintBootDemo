package com.company.springboot.enums;

import lombok.Getter;

/**
 * @author Min Xu
 * @description Result code
 */
@Getter
public enum ResultCode {

  SUCCESS(1000, "Success"),
  FAILED(1001, "Failure"),
  VALIDATE_FAILED(1002, "Invalid parameter"),
  ERROR(5000, "Unknown error");

  private int code;
  private String msg;

  ResultCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}
