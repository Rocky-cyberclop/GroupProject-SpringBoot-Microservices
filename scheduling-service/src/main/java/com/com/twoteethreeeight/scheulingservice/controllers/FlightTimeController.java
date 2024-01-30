package com.com.twoteethreeeight.scheulingservice.controllers;

import com.com.twoteethreeeight.scheulingservice.dao.FlightTimeDao;
import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import com.com.twoteethreeeight.scheulingservice.request.AddNewFlightTime;
import com.com.twoteethreeeight.scheulingservice.response.ResponseMessage;
import com.com.twoteethreeeight.scheulingservice.services.FlightTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flight-time")
public class FlightTimeController {

    @Autowired
    private FlightTimeDao flightTimeDao;
    @Autowired
    private FlightTimeService flightTimeService;

    @GetMapping
    public ResponseEntity<List<FlightTime>> getAllFlightTime() {
        List<FlightTime> flightTimes = flightTimeDao.findAll();
        return new ResponseEntity<>(flightTimes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> addNewFlightTime(@RequestBody AddNewFlightTime request) {
        return flightTimeService.addNewFlightTime(request);
    }


}
