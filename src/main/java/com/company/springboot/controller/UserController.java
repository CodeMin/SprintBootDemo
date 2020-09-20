package com.company.springboot.controller;

import com.company.springboot.dto.UserDto;
import com.company.springboot.entity.UserEntity;
import com.company.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Min Xu
 * @description User controller
 */

@Api(tags = "Users")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  @Autowired
  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @ApiOperation("Get a user by ID")
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity getUserById(@PathVariable("id") Long id) {
    Optional<UserEntity> userEntity = userService.getUserById(id);
    if (userEntity.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(convertToDto(userEntity.get()));
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @ApiOperation("Create a user")
  @PostMapping
  public void createUser(@Valid @RequestBody UserDto userDto) {
    UserEntity userEntity = convertToEntity(userDto);
    userService.saveUser(userEntity);
  }

  private UserDto convertToDto(UserEntity userEntity) {
    UserDto userDto = modelMapper.map(userEntity, UserDto.class);
    return userDto;
  }

  private UserEntity convertToEntity(UserDto userDto) {
    UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
    return userEntity;
  }
}
