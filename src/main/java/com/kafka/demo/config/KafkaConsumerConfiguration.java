package com.kafka.demo.config;

import com.kafka.demo.dto.Employee;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

      @Bean
      public Map<String,Object> consumerConfigs(){
          Map<String,Object> props = new HashMap<>();
          props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
          props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
          props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
          props.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
          props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Employee.class.getName()); // Ensure the type is correctly set
          return props;
      }

      @Bean
      public ConsumerFactory<String,Object> consumerFactory(){
          return new DefaultKafkaConsumerFactory<>(consumerConfigs());
      }

      @Bean
      public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,Object>> kafkaListenerContainerFactory(){
          ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
          factory.setConsumerFactory(consumerFactory());
          factory.setConcurrency(5);
          return factory;
      }


}