package com.twoteethreeeight.userservice.controllers;

import com.twoteethreeeight.userservice.models.User;
import com.twoteethreeeight.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
    }

    /*This function is just for create test User and not need to reuse*/
    @GetMapping("createTestUser")
    public ResponseEntity<List<User>> createTestUser(){
        return new ResponseEntity<List<User>>(userService.createTestUser(), HttpStatus.OK);
    }

}
