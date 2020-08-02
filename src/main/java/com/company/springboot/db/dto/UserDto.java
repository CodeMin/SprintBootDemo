package com.company.springboot.db.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("User")
public class UserDto {

  @ApiModelProperty("User ID")
  @NotNull(message = "ID cannot be empty.")
  private Long id;

  @ApiModelProperty("Username")
  @NotNull(message = "Username cannot be empty.")
  @Size(min = 5, max = 20, message = "The length of username must be between 5 and 20.")
  private String username;

  @ApiModelProperty("Password")
  @NotNull(message = "Password cannot be empty.")
  @Size(min = 6, max = 20, message = "The length of password must be between 6 and 20.")
  private String password;

  @ApiModelProperty("Email")
  @NotNull(message = "Email cannot be empty.")
  @Email(message = "Invalid email format.")
  private String email;

  @ApiModelProperty("Phone number")
  @NotNull(message = "Phone number cannot be empty.")
  private String phone;

}
