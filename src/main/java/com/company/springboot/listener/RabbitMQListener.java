package com.company.springboot.listener;

import com.company.springboot.entity.UserEntity;
import com.company.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RabbitMQListener {

  private final static Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

  @Autowired
  UserService userService;

  @Autowired
  AmqpTemplate amqpTemplate;

  @RabbitListener(queues = "topic.user.sendqueue")
  @RabbitHandler
  public void processMessage(String message) throws Throwable {
    logger.info("Received message");
    Long userId = null;
    try {
      userId = Long.parseLong(message);
    } catch (NumberFormatException e) {
      logger.error("Not a user ID: {}", e.getMessage());
    }

    Optional<UserEntity> userEntityOptional = userService.getUserById(userId);
    if (userEntityOptional.isPresent()) {
      UserEntity userEntity = userEntityOptional.get();
      logger.info("Send message back");
      amqpTemplate.convertAndSend("topicExchange", "topic.user.routingkey.sendback", userEntity.toString());
    }
  }
}
