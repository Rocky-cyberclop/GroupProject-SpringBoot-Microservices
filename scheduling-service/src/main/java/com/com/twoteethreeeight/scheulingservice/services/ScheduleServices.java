package com.com.twoteethreeeight.scheulingservice.services;

import com.com.twoteethreeeight.scheulingservice.dao.AirplaneDao;
import com.com.twoteethreeeight.scheulingservice.dao.AirportDao;
import com.com.twoteethreeeight.scheulingservice.dao.FlightTimeDao;
import com.com.twoteethreeeight.scheulingservice.dao.ScheduleDao;
import com.com.twoteethreeeight.scheulingservice.dto.ResultDateWithIndex;
import com.com.twoteethreeeight.scheulingservice.helpers.ScheduleHelpers;
import com.com.twoteethreeeight.scheulingservice.models.Airplane;
import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import com.com.twoteethreeeight.scheulingservice.models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServices {
    @Autowired
    private ScheduleHelpers scheduleHelpers;

    @Autowired
    private AirportDao airportDao;

    @Autowired
    private FlightTimeDao flightTimeDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private AirplaneDao airplaneDao;

    public ResponseEntity<String> doSchedule(String startDate, String endDate) {
        LocalDateTime startDateParse = scheduleHelpers.transperStrToLocalDateTime(startDate);
        LocalDateTime endDateParse = scheduleHelpers.transperStrToLocalDateTime(endDate);
        if (scheduleHelpers.compareTwoDates(startDateParse, endDateParse)) {
            return new ResponseEntity<>("The start date cannot be greater than the end date.", HttpStatus.OK);
        }
        List<Schedule> scheduleList = new ArrayList<>();
        List<Airport> airports = airportDao.findAll();

        // update start time to schedule
        airports.forEach(airport -> {
            airport.getAirplanes().forEach(airplane -> {
                airplane.setAvailableTime(startDateParse);
            });

            airport.getRunways().forEach(runway -> {
                runway.setAvailableTime(startDateParse);
            });
        });

        List<FlightTime> flightTimes = flightTimeDao.findAll();
        int totalAirport = airports.size();
        int breakPoint = 0;
        while(breakPoint == 0) {
            for (int i = 0; i < totalAirport; i++) {
                Airport lastDestination = new Airport();
                lastDestination.setName("");
                int totalAirplanes = airports.get(i).getAirplanes().size();
                for (int j = 0; j < totalAirplanes; j++) {

                    // find the next destination
                    lastDestination =
                            scheduleHelpers.calculateLastDestination(totalAirport, lastDestination.getName(), airports.get(i).getName(), airports);

                    // get estimate time to fly
                    float ETime = scheduleHelpers.getETime(flightTimes, airports.get(i).getName(), lastDestination.getName());

                    // cal start time (+1h to prepare)
                    LocalDateTime readyTime = scheduleHelpers.calculateDate(airports.get(i).getAirplanes().get(0).getAvailableTime(), 1L);


                    // find the most reasonable time to take off
                    ResultDateWithIndex smallestDpTime = scheduleHelpers.findTheSmallestTime(airports.get(i).getRunways(), readyTime);

                    if(!scheduleHelpers.compareTwoDates(readyTime, smallestDpTime.getDate())) {
                        readyTime = smallestDpTime.getDate();
                    }

                    // cal the estimate arrival time
                    LocalDateTime timeArrival = scheduleHelpers.calculateDate(readyTime, ETime);

                    int indexAirport = 0;
                    for (int k = 0; k < totalAirport; k++) {
                        if(lastDestination.getName().equals(airports.get(k).getName())) {
                            indexAirport = k;
                            break;
                        }
                    }

                    // find the most reasonable time to landing
                    ResultDateWithIndex smallestDesTime = scheduleHelpers.findTheSmallestTime(airports.get(indexAirport).getRunways(), timeArrival);

                    if(!scheduleHelpers.compareTwoDates(timeArrival, smallestDesTime.getDate())) {
                        float additionalHours = scheduleHelpers.calculateDifferenceInHours(timeArrival, smallestDesTime.getDate());
                        readyTime = scheduleHelpers.calculateDate(readyTime, additionalHours);
                        timeArrival = smallestDesTime.getDate();
                    }


                    // update time available of two runways in two airports
                    airports.get(i).getRunways().get(smallestDpTime.getIndex()).setAvailableTime(scheduleHelpers.calculateDate(readyTime, 1F));
                    airports.get(indexAirport).getRunways().get(smallestDesTime.getIndex()).setAvailableTime(scheduleHelpers.calculateDate(timeArrival, 1F));

                    // update time available of two airplanes
                    airports.get(indexAirport).getAirplanes().
                            add(new Airplane(airports.get(i).getAirplanes().get(0).getId(), airports.get(i).getAirplanes().get(0).getName(), timeArrival));

                    Schedule schedule = new Schedule();
                    schedule.setFrom(airports.get(i));
                    schedule.setTo(lastDestination);
                    schedule.setAirplane(airports.get(i).getAirplanes().get(0));
                    schedule.setTakeOffTime(readyTime);
                    schedule.setArrivalTime(timeArrival);
                    scheduleList.add(schedule);

                    // remove this airplane from airport
                    airports.get(i).getAirplanes().remove(airports.get(i).getAirplanes().get(0));

                    if(scheduleHelpers.compareTwoDates(timeArrival, endDateParse)) {
                        breakPoint = 1;
                    }
                }
                if (breakPoint == 1) break;
            }
        }

        List<Airplane> airplanes = new ArrayList<>();
        airports.forEach(airport -> {
            airplanes.addAll(airport.getAirplanes());
        });

        airplaneDao.saveAll(airplanes);
        airportDao.saveAll(airports);
        scheduleDao.saveAll(scheduleList);

        return new ResponseEntity<>("Scheduling successfully!", HttpStatus.OK);
    }

    public ResponseEntity<List<Schedule>> getAllSchedule() {
        return new ResponseEntity<>(scheduleDao.findAll(), HttpStatus.OK);
    }

}
