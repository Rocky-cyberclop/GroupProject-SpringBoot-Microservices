package com.com.twoteethreeeight.scheulingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "schedule_state")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleState {
    @Id
    private String id;
    private LocalDateTime lastScheduledTime;
    private int totalSchedules;
}
