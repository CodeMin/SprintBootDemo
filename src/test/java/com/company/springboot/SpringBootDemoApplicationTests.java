package com.company.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class SpringBootDemoApplicationTests {

	private final static Logger logger = LoggerFactory.getLogger(SpringBootDemoApplicationTests.class);

	private final static int THREAD_NUM = 2000;
	private final static String url = "http://localhost:8080/api/v1/users";

	//RestTemplate restTemplate = new RestTemplate();

	@Autowired
	AmqpTemplate amqpTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void testHighConcurrency() {
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

				String userIdMsg = "1"; // user ID
				amqpTemplate.convertAndSend("topicExchange", "topic.user.routingkey.send", userIdMsg);
//			ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class);
//			assertEquals(response.getStatusCode(), HttpStatus.OK);
				logger.info(Thread.currentThread().getName() + " is done!");
			}).start();
		}
	}

}
