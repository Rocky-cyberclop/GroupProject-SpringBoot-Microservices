package com.com.twoteethreeeight.scheulingservice.controllers;

import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.request.AddAirportRequest;
import com.com.twoteethreeeight.scheulingservice.response.ResponseMessage;
import com.com.twoteethreeeight.scheulingservice.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/aiport")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @PostMapping
    public ResponseEntity<ResponseMessage> addNewAirport(@RequestBody AddAirportRequest request) {
        return airportService.addNewAirport(request);
    }
}
