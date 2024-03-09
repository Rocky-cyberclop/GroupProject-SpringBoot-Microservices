package com.com.twoteethreeeight.scheulingservice.dao;

import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightTimeDao extends MongoRepository<FlightTime, String> {

    @Query("{from: ?0, to: ?1}")
    Optional<FlightTime> findFlightTimeByFromAndTo(ObjectId from, ObjectId to);

    @Query("{ $or : [{\"from\": ?0}, {\"to\": ?0}] }")
    List<FlightTime> getListFlightTimeFromOneAirport(ObjectId airport);

    @Query("{from: ?0, to: ?1}")
    Optional<FlightTime> findFlightTimeByFromAndTo(String from, String to);
}
