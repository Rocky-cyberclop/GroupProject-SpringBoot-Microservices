package com.twoteethreeeight.seatservice.controllers;

import com.twoteethreeeight.seatservice.models.SeatDetail;
import com.twoteethreeeight.seatservice.services.SeatDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("seat")
public class SeatDetailController {
    @Autowired
    private SeatDetailService seatDetailService;

    @RequestMapping("all")
    public ResponseEntity<List<SeatDetail>> getAllSeatDeatails(){
        System.out.println(seatDetailService.getAll());
        return new ResponseEntity<>(seatDetailService.getAll(), HttpStatus.OK);
    }
}
