package com.com.twoteethreeeight.scheulingservice.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddAirplaneRequest {
    private String name;
    private LocalDateTime availableTime;
    // airport id
    private String currentPosition;
}
