package com.twoteethreeeight.userservice.services;

import com.twoteethreeeight.userservice.models.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(ObjectId objectId);
    List<User> getAllUser();

    /*This function is just for create test User and not need to reuse*/
    List<User> createTestUser();
}
