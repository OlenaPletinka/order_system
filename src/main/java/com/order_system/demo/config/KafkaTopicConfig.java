package com.order_system.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
  @Bean
  public NewTopic orderConfirmationTopic(){
    return TopicBuilder.name("order_confirmation")
              .build();
  }
}
