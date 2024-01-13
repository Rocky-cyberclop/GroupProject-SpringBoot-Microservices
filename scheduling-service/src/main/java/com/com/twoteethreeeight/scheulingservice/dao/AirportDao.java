package com.com.twoteethreeeight.scheulingservice.dao;

import com.com.twoteethreeeight.scheulingservice.models.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirportDao extends MongoRepository<Airport, String> {
}
