package com.company.springboot.controller;

import com.company.springboot.enums.ResponseCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

  private static final long serialVersionUID = -7526485647777509317L;

  private Integer code;
  private String message;
  private Object data;

  public Response() { }

  public Response(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public static Response success() {
    Response response = new Response();
    response.setResultCode(ResponseCode.SUCCESS);
    return response;
  }

  public static Response success(Object data) {
    Response response = new Response();
    response.setResultCode(ResponseCode.SUCCESS);
    response.setData(data);
    return response;
  }

  public static Response failure(ResponseCode responseCode, String message) {
    Response response = new Response();
    response.setResultCode(responseCode);
    response.setMessage(message);
    return response;
  }

  public static Response failure(ResponseCode responseCode, Object data) {
    Response response = new Response();
    response.setResultCode(responseCode);
    response.setData(data);
    return response;
  }

  public void setResultCode(ResponseCode responseCode) {
    this.code = responseCode.getCode();
    this.message = responseCode.getMessage();
  }

}
