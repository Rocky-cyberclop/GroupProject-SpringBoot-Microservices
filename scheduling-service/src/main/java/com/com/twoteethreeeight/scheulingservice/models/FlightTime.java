package com.com.twoteethreeeight.scheulingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "flight_time")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightTime {
    @Id
    private String id;
    @DocumentReference
    private Airport from;
    @DocumentReference
    private Airport to;
    private float EstimatedTime;
}
