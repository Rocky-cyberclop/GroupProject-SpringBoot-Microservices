package com.com.twoteethreeeight.scheulingservice.controllers;

import com.com.twoteethreeeight.scheulingservice.models.Airplane;
import com.com.twoteethreeeight.scheulingservice.request.AddAirplaneRequest;
import com.com.twoteethreeeight.scheulingservice.response.ResponseMessage;
import com.com.twoteethreeeight.scheulingservice.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedule/airplane")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @PostMapping
    public ResponseEntity<ResponseMessage> addNewAirplane(@RequestBody AddAirplaneRequest request) {
        return airplaneService.addNewAirplane(request);
    }

    @PutMapping("/{airplaneId}")
    public ResponseEntity<ResponseMessage> editAirplaneInfo(@PathVariable("airplaneId") String airplaneId, @RequestBody Airplane newAirplane) {
        return airplaneService.editAirplane(airplaneId, newAirplane);
    }
}
