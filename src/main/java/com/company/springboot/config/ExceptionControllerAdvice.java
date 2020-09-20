package com.company.springboot.config;

import com.company.springboot.enums.ResultCode;
import com.company.springboot.exception.APIException;
import com.company.springboot.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Min Xu
 * @description Global exception handler
 */

//@RestControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(APIException.class)
  public ResultVO<String> APIExceptionHandler(APIException e) {
    return new ResultVO<>(ResultCode.FAILED, e.getMsg());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
    return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
  }

}
