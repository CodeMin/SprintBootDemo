package com.company.springboot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {

  /* Success */
  SUCCESS(1, "Success"),

  /* Parameter Error */
  PARAM_IS_INVALID(1001, "Parameter is invalid"),
  PARAM_IS_EMPTY(1002, "Parameter is empty"),

  /* Data Error */
  RESOURCE_NOT_FOUND(2001, "Resource not found"),
  RESOURCE_ALREADY_EXISTS(2002, "Resource already exists"),

  /* Permission Error */
  NO_ACCESS_PERMISSION(3001, "No access permission"),

  /* System Error */
  SYSTEM_UNKNOWN_ERROR(5001, "Unknown error");

  private Integer code;
  private String message;

  public static String getMessage(String name) {
    for (ResponseCode item : ResponseCode.values()) {
      if (item.name().equals(name)) {
        return item.message;
      }
    }
    return name;
  }

  public static Integer getCode(String name) {
    for (ResponseCode item : ResponseCode.values()) {
      if (item.name().equals(name)) {
        return item.code;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.name();
  }

}
