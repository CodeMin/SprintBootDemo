package com.company.springboot.service.impl;

import com.company.springboot.domain.entity.User;
import com.company.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Override
  public String createUser(User user) {
    return "success";
  }
}
