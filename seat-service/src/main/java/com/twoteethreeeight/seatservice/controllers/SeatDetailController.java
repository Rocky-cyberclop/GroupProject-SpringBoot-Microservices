package com.twoteethreeeight.seatservice.controllers;


import com.twoteethreeeight.seatservice.services.impl.SeatDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatDetailController {

    private final SeatDetailServiceImpl seatDetailService;
    @GetMapping("/booking")
    public ResponseEntity<?> bookingSeat(@RequestParam("id") ObjectId id,
                                         @RequestParam("idSchedule") String idSchedule){
        seatDetailService.bookingSeat(id, new ObjectId(idSchedule));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cancel")
    public ResponseEntity<?> destroySeat(@RequestParam("id") ObjectId id,
                                         @RequestParam("idSchedule") String idSchedule){
        seatDetailService.cancelSeat(id, new ObjectId(idSchedule));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllSeats(@RequestParam("id") String idSchedule){
        return new ResponseEntity<>(seatDetailService.seatDetailsByScheduleId(new ObjectId(idSchedule)), HttpStatus.OK);
    }

}
