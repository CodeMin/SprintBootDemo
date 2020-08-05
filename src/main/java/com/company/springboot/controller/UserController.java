package com.company.springboot.controller;

import com.company.springboot.domain.entity.User;
import com.company.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Min Xu
 * @description User controller
 */

@Api(tags = "Users")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation("Get a user by ID")
  @GetMapping("/{userId}")
  public User getUserById() {
    User user = new User();
    user.setId(0L);
    user.setUsername("test");
    return user;
  }

  @ApiOperation("Create a user")
  @PostMapping
  public String createUser(@Valid @RequestBody User user) {
    return userService.createUser(user);
  }
}
