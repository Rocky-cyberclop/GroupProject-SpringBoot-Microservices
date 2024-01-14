package com.com.twoteethreeeight.scheulingservice.controllers;

import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.services.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/manage")
public class ManageController {

    @Autowired
    private ManageService manageService;

    @GetMapping("/schedule")
    public ResponseEntity<Map<String, Object>> fetchPaginationSchedule(@RequestParam(required = false, defaultValue = "20") int size, @RequestParam(required = false, defaultValue = "1") int currentPage) {
        return manageService.getAllSchedule(size, currentPage);
    }

    @GetMapping("/airport")
    public ResponseEntity<List<Airport>> fetchAllAirport() {
        return manageService.getAllAirportInfo();
    }
}
