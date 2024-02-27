package com.com.twoteethreeeight.scheulingservice.dao;

import com.com.twoteethreeeight.scheulingservice.models.Schedule;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleDao extends MongoRepository<Schedule, String> {

    @Aggregation({"{ $lookup: {from : \"airport\", localField: \"from\", foreignField: \"_id\", as: \"departure\"}}",
            "{ $lookup: {from : \"airport\", localField: \"to\", foreignField: \"_id\", as: \"destination\"}}",
            "{ $lookup: {from : \"airplane\", localField: \"airplane\", foreignField: \"_id\", as: \"airplaneInfo\"}}",
            "{ $match: { 'departure.0.name': { $regex: ?0, $options: 'i' }, 'destination.0.name': { $regex: ?1, $options: 'i' }, 'takeOffTime': { $gte: ?2, $lt: ?3 } } }"})
    List<Schedule> searchSchedule(String from, String to, LocalDateTime startTime1, LocalDateTime startTime2);

}
