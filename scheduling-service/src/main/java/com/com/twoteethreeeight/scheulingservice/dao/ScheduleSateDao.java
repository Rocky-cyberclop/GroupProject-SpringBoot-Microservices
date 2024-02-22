package com.com.twoteethreeeight.scheulingservice.dao;

import com.com.twoteethreeeight.scheulingservice.models.ScheduleState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleSateDao extends MongoRepository<ScheduleState, String> {
}
