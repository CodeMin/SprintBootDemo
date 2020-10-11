package com.company.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  // Declare the queue
  @Bean
  public Queue sendQueue() {
    return new Queue("topic.user.sendqueue", true);
  }

  // Declare the exchange
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("topicExchange");
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(sendQueue()).to(exchange()).with("topic.user.routingkey.send");
  }
}
