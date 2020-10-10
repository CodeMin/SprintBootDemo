package com.company.springboot.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

  private final static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

  private final static int THREAD_NUM = 1000;
  private final static String url = "http://localhost:8080/api/v1/users";

  RestTemplate restTemplate = new RestTemplate();

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void testConcurrentlyGetUserById() {
    CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
    for (int i = 0; i < THREAD_NUM; i++) {
      new Thread(() -> {
        logger.info(Thread.currentThread().getName() + " is ready.");
        try {
          countDownLatch.countDown();
          countDownLatch.await();
        } catch (InterruptedException e) {
          logger.error(e.getMessage());
        }

        ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        logger.info(Thread.currentThread().getName() + " is done!");
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//          JsonNode root = mapper.readTree(response.getBody());
//          JsonNode id = root.path("id");
//          assertEquals(id.asLong(), 1L);
//        } catch (JsonProcessingException e) {
//          logger.error(e.getMessage());
//        }
      }).start();
    }
  }
}