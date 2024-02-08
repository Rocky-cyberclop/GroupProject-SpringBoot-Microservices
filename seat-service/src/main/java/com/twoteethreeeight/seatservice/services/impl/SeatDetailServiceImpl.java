package com.twoteethreeeight.seatservice.services.impl;

import com.twoteethreeeight.seatservice.enums.Class;
import com.twoteethreeeight.seatservice.enums.Status;
import com.twoteethreeeight.seatservice.models.Seat;
import com.twoteethreeeight.seatservice.models.SeatDetail;
import com.twoteethreeeight.seatservice.repositories.SeatDetailRepository;
import com.twoteethreeeight.seatservice.services.SeatDetailService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatDetailServiceImpl implements SeatDetailService {

    @Autowired
    private SeatDetailRepository seatDetailRepository;

    //This function for testing prupose and can be removed with no erro
    @Override
//    @Bean
    public void generateTestSeatDetail() {
        List<SeatDetail> seatDetails = new ArrayList<>();
        for (int i=0; i<30; i++){
            Seat seat = new Seat();
            seat.setAClass(Class.BUSINESS);
            seat.setStatus(Status.AVAILABLE);
            SeatDetail seatDetail = new SeatDetail();
            seatDetail.setSeat(seat);
            seatDetail.setScheduleId(new ObjectId("65a4b49b9bab87676502e19f"));
            seatDetails.add(seatDetail);
        }

        for (int i=0; i<30; i++){
            Seat seat = new Seat();
            seat.setAClass(Class.DELUXE);
            seat.setStatus(Status.AVAILABLE);
            SeatDetail seatDetail = new SeatDetail();
            seatDetail.setSeat(seat);
            seatDetail.setScheduleId(new ObjectId("65a4b49b9bab87676502e19f"));
            seatDetails.add(seatDetail);
        }

        for (int i=0; i<40; i++){
            Seat seat = new Seat();
            seat.setAClass(Class.CLASSIC);
            seat.setStatus(Status.AVAILABLE);
            SeatDetail seatDetail = new SeatDetail();
            seatDetail.setSeat(seat);
            seatDetail.setScheduleId(new ObjectId("65a4b49b9bab87676502e19f"));
            seatDetails.add(seatDetail);
        }

        seatDetailRepository.saveAll(seatDetails);
    }
    //This function for testing prupose and can be removed with no erro
    @Override
//    @Bean
    public void updateSeatDetail() {
        SeatDetail seatDetail = seatDetailRepository.findById(new ObjectId("65b8da0675d0007fe433d9d5")).orElseThrow();
        seatDetail.getSeat().setStatus(Status.BOOKED);
        seatDetailRepository.save(seatDetail);
    }

    @Override
    public List<SeatDetail> getAll() {
        return seatDetailRepository.findAll();
    }
}
