package com.com.twoteethreeeight.scheulingservice.services;

import com.com.twoteethreeeight.scheulingservice.dao.AirportDao;
import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.models.Runway;
import com.com.twoteethreeeight.scheulingservice.request.AddAirportRequest;
import com.com.twoteethreeeight.scheulingservice.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AirportService {
    @Autowired
    private AirportDao airportDao;


    @Transactional
    public ResponseEntity<ResponseMessage> addNewAirport(AddAirportRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<Runway> runways = new ArrayList<>();
        for (int i = 0; i < request.getTotalRunways(); i++) {
            Runway runway = new Runway(request.getName() + "-Runway" + (i+1), LocalDateTime.now());
            runways.add(runway);
        }
        Airport newAirport = Airport.builder()
                .name(request.getName())
                .lastDestination("")
                .runways(runways)
                .build();
        Airport savedAirport = airportDao.save(newAirport);
        responseMessage.setMessage("Add new airport successfully!");
        responseMessage.setData(savedAirport);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
