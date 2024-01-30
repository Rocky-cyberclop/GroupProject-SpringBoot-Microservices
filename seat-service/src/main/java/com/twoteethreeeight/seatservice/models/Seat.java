package com.twoteethreeeight.seatservice.models;

import com.twoteethreeeight.seatservice.enums.Class;
import com.twoteethreeeight.seatservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seat {
    @Id
    private ObjectId _id;

    private Class aClass;

    private Status status;
}
