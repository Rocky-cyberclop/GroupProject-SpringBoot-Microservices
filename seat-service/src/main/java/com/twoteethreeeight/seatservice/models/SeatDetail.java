package com.twoteethreeeight.seatservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("seat_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDetail {
    @Id
    private ObjectId _id;

    private ObjectId scheduleId;

    private Seat seat;
}
