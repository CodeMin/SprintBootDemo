package com.company.springboot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

  @NotNull
  private Long id;

  @NotNull
  private String name;

  private String email;

  private String phone;

}
