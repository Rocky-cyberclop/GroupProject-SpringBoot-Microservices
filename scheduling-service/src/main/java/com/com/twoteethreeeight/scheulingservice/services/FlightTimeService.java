package com.com.twoteethreeeight.scheulingservice.services;

import com.com.twoteethreeeight.scheulingservice.dao.AirplaneDao;
import com.com.twoteethreeeight.scheulingservice.dao.AirportDao;
import com.com.twoteethreeeight.scheulingservice.dao.FlightTimeDao;
import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import com.com.twoteethreeeight.scheulingservice.request.AddNewFlightTime;
import com.com.twoteethreeeight.scheulingservice.response.ResponseMessage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FlightTimeService {
    @Autowired
    private FlightTimeDao flightTimeDao;
    @Autowired
    private AirportDao airportDao;

    @Transactional
    public ResponseEntity<ResponseMessage> addNewFlightTime(AddNewFlightTime request) {
        ResponseMessage responseMessage = new ResponseMessage();
        ObjectId airportFromObjectId = new ObjectId(request.getFromAirportId());
        ObjectId airportToObjectId = new ObjectId(request.getToAirportId());
        Optional<FlightTime> flightTime = flightTimeDao.findFlightTimeByFromAndTo(airportFromObjectId, airportToObjectId);

        if (flightTime.isPresent()) {
            flightTime.get().setEstimatedTime(request.getEstimatedTime());
            flightTimeDao.save(flightTime.get());
        } else {
            Airport airportFrom = airportDao.findById(request.getFromAirportId()).orElseThrow();
            Airport airportTo = airportDao.findById(request.getToAirportId()).orElseThrow();
            FlightTime newFlightTime = new FlightTime();
            newFlightTime.setFrom(airportFrom);
            newFlightTime.setTo(airportTo);
            newFlightTime.setEstimatedTime(request.getEstimatedTime());
            flightTimeDao.save(newFlightTime);
        }
        responseMessage.setMessage("Save new flight time successfully!");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
