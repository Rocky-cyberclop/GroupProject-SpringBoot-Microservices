package com.com.twoteethreeeight.scheulingservice.dao;

import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightTimeDao extends MongoRepository<FlightTime, String> {
}
