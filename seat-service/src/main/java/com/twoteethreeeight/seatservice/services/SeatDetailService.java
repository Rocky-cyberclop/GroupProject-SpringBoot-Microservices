package com.twoteethreeeight.seatservice.services;

import com.twoteethreeeight.seatservice.models.SeatDetail;

import org.bson.types.ObjectId;


import java.util.List;

public interface SeatDetailService {
    //This function for testing prupose and can be removed with no erro
    public void generateTestSeatDetail();
    //This function for testing prupose and can be removed with no erro
    public void updateSeatDetail();

    void bookingSeat(ObjectId id, ObjectId idSchedule);

    void cancelSeat(ObjectId id, ObjectId idSchedule);

    List<SeatDetail> seatDetails();

    List<SeatDetail> seatDetailsByScheduleId(ObjectId scheduleId);

    void listenSeatTopic(List<SeatDetail> detailList);

}
