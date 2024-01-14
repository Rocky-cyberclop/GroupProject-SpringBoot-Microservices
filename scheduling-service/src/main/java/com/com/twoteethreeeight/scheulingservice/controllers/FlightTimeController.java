package com.com.twoteethreeeight.scheulingservice.controllers;

import com.com.twoteethreeeight.scheulingservice.dao.FlightTimeDao;
import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flight-time")
public class FlightTimeController {

    @Autowired
    private FlightTimeDao flightTimeDao;

    @GetMapping
    public ResponseEntity<List<FlightTime>> getAllFlightTime() {
        List<FlightTime> flightTimes = flightTimeDao.findAll();
        return new ResponseEntity<>(flightTimes, HttpStatus.OK);
    }


}
