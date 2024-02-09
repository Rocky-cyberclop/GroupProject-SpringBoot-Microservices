package com.twoteethreeeight.seatservice.services.impl;

import com.twoteethreeeight.seatservice.enums.Class;
import com.twoteethreeeight.seatservice.enums.Status;
import com.twoteethreeeight.seatservice.models.Seat;
import com.twoteethreeeight.seatservice.models.SeatDetail;
import com.twoteethreeeight.seatservice.repositories.SeatDetailRepository;
import com.twoteethreeeight.seatservice.services.KafkaService.SeatProducerService;
import com.twoteethreeeight.seatservice.services.SeatDetailService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatDetailServiceImpl implements SeatDetailService {


    private final SeatDetailRepository seatDetailRepository;

    private final SeatProducerService seatProducerService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    //This function for testing prupose and can be removed with no erro
    @Override
//    @Bean
    public void generateTestSeatDetail() {
        List<SeatDetail> seatDetails = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Seat seat = new Seat();
            seat.setAClass(Class.BUSINESS);
            seat.setStatus(Status.AVAILABLE);
            SeatDetail seatDetail = new SeatDetail();
            seatDetail.setSeat(seat);
            seatDetail.setScheduleId(new ObjectId("65a4b49b9bab87676502e19f"));
            seatDetails.add(seatDetail);
        }

        for (int i = 0; i < 30; i++) {
            Seat seat = new Seat();
            seat.setAClass(Class.DELUXE);
            seat.setStatus(Status.AVAILABLE);
            SeatDetail seatDetail = new SeatDetail();
            seatDetail.setSeat(seat);
            seatDetail.setScheduleId(new ObjectId("65a4b49b9bab87676502e19f"));
            seatDetails.add(seatDetail);
        }

        for (int i = 0; i < 40; i++) {
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
    public void bookingSeat(ObjectId id, ObjectId idSchedule) {
        SeatDetail seatDetail = seatDetailRepository.findById(id).get();
            seatDetail.getSeat().setStatus(Status.BOOKING);
            seatDetailRepository.save(seatDetail);
        List<SeatDetail> seatDetails = seatDetailRepository.findAllByScheduleId(idSchedule);
        seatProducerService.setSeat(seatDetails);
    }

    @Override
    public void cancelSeat(ObjectId id, ObjectId idSchedule) {
        SeatDetail seatDetail = seatDetailRepository.findById(id).get();
        seatDetail.getSeat().setStatus(Status.AVAILABLE);
        seatDetailRepository.save(seatDetail);
        List<SeatDetail> seatDetails = seatDetailRepository.findAllByScheduleId(idSchedule);
        seatProducerService.setSeat(seatDetails);
    }

    @Override
    public List<SeatDetail> seatDetails() {
        return seatDetailRepository.findAll();
    }

    @Override
    public List<SeatDetail> seatDetailsByScheduleId(ObjectId scheduleId) {
        return seatDetailRepository.findAllByScheduleId(scheduleId);
    }

    @Override
    @KafkaListener(groupId = "seatGroup", topics = "seatTopic")
    public void listenSeatTopic(List<SeatDetail> detailList) {
        System.out.println(detailList);
        simpMessagingTemplate.convertAndSend("/topic/seatTopic", detailList);
    }
}
