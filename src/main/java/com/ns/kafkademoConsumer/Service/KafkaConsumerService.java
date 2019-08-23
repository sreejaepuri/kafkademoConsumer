package com.ns.kafkademoConsumer.Service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

@Service
public class KafkaConsumerService {
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @KafkaListener(topics = "saro", groupId = "karthik")
    public void Consumed(String message) {
        System.out.println("Listened messages" + message);
    }

    @KafkaListener(topics = "saroja", groupId = "honey", containerFactory = "kafkaListenerContainerFactory")
    public void ConsumeJson(User user) {
        System.out.println("Consumed JSON messages" + user);

    }

    @KafkaHandler
    @KafkaListener(id = "fileContainer", topics = "saroja", groupId = "Sreeja", containerFactory = "userKafkaListenerFactory1")
    public void ListeningFile(String data) {
        System.out.println("it is Consumer Side");
        registry.getListenerContainer("fileContainer").start();
        System.out.println("File published Successfully---------------------------->" + data);
    }

}
