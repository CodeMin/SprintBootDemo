package com.company.springboot.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTask {

  private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedRate = 60000)
  public void printCurrentTime() {
    logger.info("Current time:" + dateFormat.format(new Date()));
  }
}
