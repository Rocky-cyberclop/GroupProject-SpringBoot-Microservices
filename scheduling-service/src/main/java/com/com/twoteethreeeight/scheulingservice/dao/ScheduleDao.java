package com.com.twoteethreeeight.scheulingservice.dao;

import com.com.twoteethreeeight.scheulingservice.models.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ScheduleDao extends MongoRepository<Schedule, String> {
    @Query("{ 'from.name' : { $regex: ?0, $options: 'i' } }")
    List<Schedule> findByStartDes(String startDes);
}
