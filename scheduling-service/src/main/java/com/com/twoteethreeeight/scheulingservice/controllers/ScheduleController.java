package com.com.twoteethreeeight.scheulingservice.controllers;

import com.com.twoteethreeeight.scheulingservice.dao.ScheduleDao;
import com.com.twoteethreeeight.scheulingservice.models.Schedule;
import com.com.twoteethreeeight.scheulingservice.dto.RequestSchedule;
import com.com.twoteethreeeight.scheulingservice.services.ScheduleServices;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleServices scheduleServices;

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedule() {
        return scheduleServices.getAllSchedule();
    }

    @PostMapping
    public ResponseEntity<String> schedule(@RequestBody RequestSchedule request) {
        return scheduleServices.doSchedule(request.getStartDate(), request.getEndDate());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Schedule>> fetchSchedule(@RequestParam("from") String from,
                                                        @RequestParam("to") String to,
                                                        @RequestParam("startTime") LocalDateTime startTime) {
        return scheduleServices.searchSchedule(from, to, startTime);
    }
}
