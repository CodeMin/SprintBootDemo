package com.company.springboot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

  private static final long serialVersionUID = -8020211560124448567L;

  @NotNull
  private Long id;

  @NotNull
  private String name;

  private String email;

  private String phone;

}
