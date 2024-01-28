package com.com.twoteethreeeight.scheulingservice.request;

import com.com.twoteethreeeight.scheulingservice.models.Airplane;
import com.com.twoteethreeeight.scheulingservice.models.Runway;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAirportRequest {
    private String name;
    private int totalRunways;
}
