package com.twoteethreeeight.userservice.services;

import com.twoteethreeeight.userservice.Dto.ProfileDto;
import com.twoteethreeeight.userservice.models.User;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(ObjectId objectId);
    List<User> getAllUser();
    String loginUser(String email, String responseCode);
    String registerUser(User user, String responseCode);
    User Authenticate(String token);
    String updateUserByEmail(String responeCode, ProfileDto infoEdit);
    /*This function is just for create test User and not need to reuse*/
    List<User> createTestUser();

    ResponseEntity<String> editUserInfo(String email, ProfileDto infoEdit);
}
