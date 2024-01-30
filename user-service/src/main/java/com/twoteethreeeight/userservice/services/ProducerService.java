package com.twoteethreeeight.userservice.services;

import com.twoteethreeeight.userservice.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {
//    public  final KafkaTemplate<String , User> kafkaTemplate;
public  final KafkaTemplate<String , String> kafkaTemplate;
//    public void SendMessage(User user){
//        kafkaTemplate.send("mailTopic",user);
//    }
public void SendMessage(String email){
    kafkaTemplate.send("mailTopic",email);
}
}
