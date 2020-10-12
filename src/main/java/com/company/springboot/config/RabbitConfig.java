package com.company.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  // Declare the queues
  @Bean
  public Queue sendQueryUserQueue() {
    return new Queue("topic.user.query", true);
  }

  @Bean
  public Queue sendReplyUserQueue() {
    return new Queue("topic.user.reply", true);
  }

  // Declare the exchanges
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("topicExchange");
  }

  // Binding queue to exchange
  @Bean
  public Binding bindingQueryUser() {
    return BindingBuilder.bind(sendQueryUserQueue()).to(exchange()).with("topic.user.routingkey.query");
  }

  @Bean
  public Binding bindingReplyUser() {
    return BindingBuilder.bind(sendReplyUserQueue()).to(exchange()).with("topic.user.routingkey.reply");
  }
}
