package com.company.springboot.listener;

import com.company.springboot.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class WeChatListener implements ApplicationListener<UserCreatedEvent> {

  private static final Logger logger = LoggerFactory.getLogger(WeChatListener.class);

  @Override
  public void onApplicationEvent(UserCreatedEvent userCreatedEvent) {
    logger.info("Send WeChat template message: user {} created", userCreatedEvent.getSource());
  }
}
