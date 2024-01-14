package com.com.twoteethreeeight.scheulingservice.services;

import com.com.twoteethreeeight.scheulingservice.dao.AirplaneDao;
import com.com.twoteethreeeight.scheulingservice.dao.AirportDao;
import com.com.twoteethreeeight.scheulingservice.dao.ScheduleDao;
import com.com.twoteethreeeight.scheulingservice.dto.Pagination;
import com.com.twoteethreeeight.scheulingservice.models.Airplane;
import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageService {

    @Autowired
    private AirportDao airportDao;

    @Autowired
    private AirplaneDao airplaneDao;

    @Autowired
    private ScheduleDao scheduleDao;

    public ResponseEntity<List<Airport>> getAllAirportInfo() {
        return new ResponseEntity<>(airportDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Airplane>> getAllAirplaneInfo() {
        return new ResponseEntity<>(airplaneDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getAllSchedule(int size, int currentPage) {
        Map<String, Object> result = new HashMap<>();
        Pagination pagination = new Pagination();
        Pageable pageable = PageRequest.of(currentPage - 1, size);
        Page<Schedule> page = scheduleDao.findAll(pageable);
        pagination.setSize(size);
        pagination.setCurrentPage(currentPage);
        pagination.setTotalPage(page.getTotalPages());
        pagination.setTotalResult((int) page.getTotalElements());
        result.put("data", page.getContent());
        result.put("pagination", pagination);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
