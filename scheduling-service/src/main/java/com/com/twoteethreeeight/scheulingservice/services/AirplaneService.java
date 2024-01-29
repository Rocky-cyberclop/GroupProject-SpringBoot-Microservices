package com.com.twoteethreeeight.scheulingservice.services;

import com.com.twoteethreeeight.scheulingservice.dao.AirplaneDao;
import com.com.twoteethreeeight.scheulingservice.dao.AirportDao;
import com.com.twoteethreeeight.scheulingservice.models.Airplane;
import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.request.AddAirplaneRequest;
import com.com.twoteethreeeight.scheulingservice.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneDao airplaneDao;
    @Autowired
    private AirportDao airportDao;


    @Transactional
    public ResponseEntity<ResponseMessage> addNewAirplane(AddAirplaneRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        Airplane airplane = new Airplane();
        airplane.setName(request.getName());
        airplane.setAvailableTime(LocalDateTime.now());
        Airplane savedAirplane = airplaneDao.save(airplane);
        Optional<Airport> airport = airportDao.findById(request.getCurrentPosition());
        if(airport.isPresent()) {
            List<Airplane> airplanes = airport.get().getAirplanes();
            airplanes.add(savedAirplane);
            airport.get().setAirplanes(airplanes);
            airportDao.save(airport.get());
        } else {
            responseMessage.setMessage("Failed to add airplane to airport!");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }

        responseMessage.setMessage("Add new airplane successfully!");
        responseMessage.setData(savedAirplane);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ResponseMessage> editAirplane(String airplaneId, Airplane newAirplane) {
        ResponseMessage responseMessage = new ResponseMessage();
        Optional<Airplane> airplane = airplaneDao.findById(airplaneId);
        if(airplane.isPresent()) {
            airplane.get().setName(newAirplane.getName());
            airplane.get().setAvailableTime(newAirplane.getAvailableTime());

            Airplane savedAirplane = airplaneDao.save(airplane.get());
            responseMessage.setMessage("Update airplane successfully!");
            responseMessage.setData(savedAirplane);
        } else {
            responseMessage.setMessage("That airplane is not exist!");
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
