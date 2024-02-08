package com.twoteethreeeight.seatservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("seat_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDetail implements Serializable {
    @Id
    private String _id;

    private ObjectId scheduleId;

    private Seat seat;
}
