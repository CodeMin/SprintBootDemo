package com.company.springboot.service;

import com.company.springboot.entity.UserEntity;
import com.company.springboot.repository.UserRepository;
import com.company.springboot.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

  private final static int THREAD_NUM = 100;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserServiceImpl userService;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void testGetUserById() {
    CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
    for (int i = 0; i < THREAD_NUM; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + " is ready.");
        try {
          countDownLatch.countDown();
          countDownLatch.await();
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          System.out.println(Thread.currentThread().getName() + " is running...");
          Optional<UserEntity> userEntity = userService.getUserById(1L);
          assertNotNull(userEntity);
          assertEquals(userEntity.get().getId(), 1L);
        } catch (Throwable throwable) {
          throwable.printStackTrace();
        }
      }).start();
    }
  }
}