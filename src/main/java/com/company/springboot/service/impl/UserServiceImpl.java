package com.company.springboot.service.impl;

import com.company.springboot.security.PermissionInfo;
import com.company.springboot.entity.UserEntity;
import com.company.springboot.repository.UserRepository;
import com.company.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @PermissionInfo(name = "Get")
  public Optional<UserEntity> getUserById(Long id) {
    return userRepository.getById(id);
  }

  @Override
  @PermissionInfo(name = "Save")
  public UserEntity saveUser(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }
}
