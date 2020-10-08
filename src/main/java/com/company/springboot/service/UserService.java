package com.company.springboot.service;

import com.company.springboot.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

  List<UserEntity> getAllUsers() throws Throwable;

  Optional<UserEntity> getUserById(Long id) throws Throwable;

  UserEntity saveUser(UserEntity userEntity) throws Throwable;

}
