package com.twoteethreeeight.seatservice.services.KafkaService;

import com.twoteethreeeight.seatservice.models.Seat;
import com.twoteethreeeight.seatservice.models.SeatDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatProducerService {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void setSeat(List<SeatDetail> seats){
        kafkaTemplate.send("seatTopic", seats);
    }
}
