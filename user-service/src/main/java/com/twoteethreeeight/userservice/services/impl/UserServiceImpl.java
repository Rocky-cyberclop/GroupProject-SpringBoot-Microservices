package com.twoteethreeeight.userservice.services.impl;

import com.twoteethreeeight.userservice.models.User;
import com.twoteethreeeight.userservice.repositories.UserRepository;
import com.twoteethreeeight.userservice.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(ObjectId objectId){
        return userRepository.findById(objectId);
    }

    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    /*This function is just for create test User and not need to reuse*/
    @Override
    public List<User> createTestUser() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFullName("Tran Anh Hao");
        user1.setEmail("rockyoperation@gmail.com");
        user1.setPhone("0355669359");
        user1.setRole("User");
        user1.setCodeTmp("adc");
        users.add(user1);
        User user2 = new User();
        user2.setFullName("Nguyen Tri Thuc");
        user2.setEmail("trithuc0416@gmail.com");
        user2.setPhone("0355611359");
        user2.setRole("User");
        user2.setCodeTmp("abc");
        users.add(user2);
        User user3 = new User();
        user3.setFullName("Thuc Tri Nguyen");
        user3.setEmail("thucb2005736@student.ctu.edu.vn");
        user3.setPhone("0355611322");
        user3.setRole("User");
        user3.setCodeTmp("cab");
        users.add(user3);


        userRepository.saveAll(users);
        return users;
    }
}
