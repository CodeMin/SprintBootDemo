package com.company.springboot.controller;

import com.company.springboot.enums.ResponseCode;
import com.company.springboot.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Min Xu
 * @description Global handler
 */

@RestControllerAdvice(basePackages = "com.company.springboot.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    return !methodParameter.getParameterType().equals(ResponseCode.class);
  }

  @Override
  public Object beforeBodyWrite(Object data,
                                MethodParameter methodParameter,
                                MediaType mediaType,
                                Class<? extends HttpMessageConverter<?>> aClass,
                                ServerHttpRequest serverHttpRequest,
                                ServerHttpResponse serverHttpResponse) {
    if (methodParameter.getGenericParameterType().equals(String.class)) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        return objectMapper.writeValueAsString(Response.success(data));
      } catch (JsonProcessingException e) {
        throw new BadRequestException("Return String type error!");
      }
    }

    if (data instanceof Response) {
      return data;
    }

    return Response.success(data);
  }
}