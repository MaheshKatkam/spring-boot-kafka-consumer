package com.springboot.kafka.consumer.service;

import com.springboot.kafka.consumer.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "kafka-message-topic-partition-3", groupId = "kafka-consumer-default-group-id")
    public void consumerMessage(String message){
        logger.info("Consumed the message: {} ", message);
    }

    @KafkaListener(topics = "kafka-message-topic-java-event-partition-5", groupId = "kafka-consumer-default-group-id")
    public void consumerEvent(Employee employee){
        logger.info("Consumed the event: {} ", employee.toString());
    }
}
