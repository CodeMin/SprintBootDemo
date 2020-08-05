package com.company.springboot.vo;

import com.company.springboot.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author Min Xu
 * @description Customized common response
 */
@Getter
@ApiModel
public class ResultVO<T> {
  @ApiModelProperty(value = "Status code", notes = "Default value 1000, which means success.")
  private int code;
  @ApiModelProperty(value = "Message", notes = "Message")
  private String msg;
  @ApiModelProperty(value = "Response data")
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
