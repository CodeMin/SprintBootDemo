package com.company.springboot.vo;

import com.company.springboot.enums.ResultCode;
import lombok.Getter;

/**
 * @author Min Xu
 * @description Customized common response
 */
@Getter
public class ResultVO<T> {
  private int code;
  private String msg;
  private T data;

  public ResultVO(T data) {
    this(ResultCode.SUCCESS, data);
  }

  public ResultVO(ResultCode resultCode, T data) {
    this.code = resultCode.getCode();
    this.msg = resultCode.getMsg();
    this.data = data;
  }
}
