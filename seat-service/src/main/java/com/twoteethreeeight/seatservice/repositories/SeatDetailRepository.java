package com.twoteethreeeight.seatservice.repositories;

import com.twoteethreeeight.seatservice.models.SeatDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDetailRepository extends MongoRepository<SeatDetail, ObjectId> {
}
