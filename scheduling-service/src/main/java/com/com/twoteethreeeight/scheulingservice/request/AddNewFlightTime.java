package com.com.twoteethreeeight.scheulingservice.request;

import com.com.twoteethreeeight.scheulingservice.models.Airport;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
public class AddNewFlightTime {
    private String fromAirportId;
    private String toAirportId;
    private float estimatedTime;
}
