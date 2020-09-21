package com.company.springboot.controller;

import com.company.springboot.dto.UserDto;
import com.company.springboot.entity.UserEntity;
import com.company.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * @author Min Xu
 * @description User controller
 */

@Tag(name = "User Controller")
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

  @Operation(summary = "Get a user by ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successful operation",
                  content = { @Content(mediaType = "application/json",
                          schema = @Schema(implementation = UserDto.class)) }),
          @ApiResponse(responseCode = "404", description = "Resource not found",
                  content = @Content)
  })
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity getUserById(@PathVariable("id") Long id) {
    Optional<UserEntity> userEntity = userService.getUserById(id);
    if (userEntity.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(convertToDto(userEntity.get()));
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @Operation(summary = "Create a user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Created successfully",
                  content = @Content),
          @ApiResponse(responseCode = "400", description = "Bad request",
                  content = @Content),
          @ApiResponse(responseCode = "417", description = "Expectation failed",
                  content = @Content)
  })
  @PostMapping
  public ResponseEntity createUser(
          @Parameter(description = "User", required = true, schema = @Schema(implementation = UserDto.class))
          @Valid @RequestBody UserDto userDto) {
    try {
      UserEntity userEntity = convertToEntity(userDto);
      userEntity = userService.saveUser(userEntity);
      if (userEntity != null) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity.getId());
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }
  }

  private UserDto convertToDto(UserEntity userEntity) {
    UserDto userDto = modelMapper.map(userEntity, UserDto.class);
    return userDto;
  }

  private UserEntity convertToEntity(UserDto userDto) {
    UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
    Date now = new Date();
    if (userEntity.getCreateTime() == null) {
      userEntity.setCreateTime(now);
    }
    userEntity.setUpdateTime(now);
    return userEntity;
  }
}
