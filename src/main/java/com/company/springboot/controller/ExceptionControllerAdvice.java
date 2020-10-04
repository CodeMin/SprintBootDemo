package com.company.springboot.controller;

import com.company.springboot.enums.ResponseCode;
import com.company.springboot.exception.BadRequestException;
import com.company.springboot.exception.NoPermissionException;
import com.company.springboot.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author Min Xu
 * @description Global exception handler
 */

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(NoPermissionException.class)
  public Response noPermissionExceptionHandler(NoPermissionException e) {
    return Response.failure(ResponseCode.NO_ACCESS_PERMISSION, e.getLocalizedMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  public Response badRequestExceptionHandler(BadRequestException e) {
    return Response.failure(ResponseCode.PARAM_IS_INVALID, e.getLocalizedMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public Response resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
    return Response.failure(ResponseCode.RESOURCE_NOT_FOUND, e.getLocalizedMessage());
  }

}
