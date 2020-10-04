package com.company.springboot.exception;

/**
 * @author Min Xu
 * @description No permission exception
 */

public class NoPermissionException extends RuntimeException {

  private static final long serialVersionUID = -4025549890896064530L;

  public NoPermissionException(String method) {
    super("No access permission to method " + method);
  }

}
