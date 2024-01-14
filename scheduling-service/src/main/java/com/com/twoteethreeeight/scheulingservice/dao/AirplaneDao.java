package com.com.twoteethreeeight.scheulingservice.dao;

import com.com.twoteethreeeight.scheulingservice.models.Airplane;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AirplaneDao extends MongoRepository<Airplane, String> {
    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    List<Airplane> searchByName(String name);
}
