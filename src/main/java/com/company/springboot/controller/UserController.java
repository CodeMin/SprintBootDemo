package com.company.springboot.controller;

import com.company.springboot.dto.UserDto;
import com.company.springboot.entity.UserEntity;
import com.company.springboot.exception.BadRequestException;
import com.company.springboot.exception.ResourceNotFoundException;
import com.company.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
                          schema = @Schema(implementation = Response.class)) })
  })
  @GetMapping("/{id}")
  @ResponseBody
  //@Cacheable(value = "user-key")
  public UserDto getUserById(@PathVariable("id") Long id) throws Throwable {
    Optional<UserEntity> userEntity = userService.getUserById(id);
    if (userEntity != null && userEntity.isPresent()) {
      logger.info("User {} found", id);
      return convertToDto(userEntity.get());
    }

    logger.info("User {} not found", id);
    throw new ResourceNotFoundException(id);
  }

  @Operation(summary = "Create a user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successful operation",
                  content = { @Content(mediaType = "application/json",
                          schema = @Schema(implementation = Response.class)) })
  })
  @PostMapping
  public Long createUser(
          @Parameter(description = "User", required = true, schema = @Schema(implementation = UserDto.class))
          @Valid @RequestBody UserDto userDto) throws Throwable {
    if (StringUtils.isEmpty(userDto.getName())) {
      logger.info("User name is empty");
      throw new BadRequestException("Name is required");
    }

    UserEntity userEntity = convertToEntity(userDto);
    userEntity = userService.saveUser(userEntity);
    if (userEntity != null) {
      logger.info("Create user {} successfully", userEntity.getId());
      return userEntity.getId();
    }

    throw new BadRequestException("Unknown error");
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
