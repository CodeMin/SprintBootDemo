package com.company.springboot.service;

import com.company.springboot.entity.UserEntity;

import java.util.Optional;

public interface UserService {

  Optional<UserEntity> getUserById(Long id) throws Throwable;

  UserEntity saveUser(UserEntity userEntity) throws Throwable;

}
