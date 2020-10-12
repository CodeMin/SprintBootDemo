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

  @RabbitListener(queues = "topic.user.query")
  @RabbitHandler
  public void processQueryUserMessage(String message) throws Throwable {
    logger.info("Received query user message");
    Long userId = null;
    try {
      userId = Long.parseLong(message);
    } catch (NumberFormatException e) {
      logger.error("Not a user ID: {}", e.getMessage());
    }

    Optional<UserEntity> userEntityOptional = userService.getUserById(userId);
    if (userEntityOptional.isPresent()) {
      UserEntity userEntity = userEntityOptional.get();
      logger.info("Reply user info");
      amqpTemplate.convertAndSend("topicExchange", "topic.user.routingkey.reply", userEntity);
    }
  }

  @RabbitListener(queues = "topic.user.reply")
  @RabbitHandler
  public void processReplyUserMessage(UserEntity message) {
    if (message != null) {
      logger.info("Received reply user message");
      logger.info("The name of user {} is {}", message.getId(), message.getName());
    } else {
      logger.warn("No any user info replied");
    }
  }
}
