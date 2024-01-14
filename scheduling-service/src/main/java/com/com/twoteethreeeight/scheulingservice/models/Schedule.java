package com.com.twoteethreeeight.scheulingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private LocalDateTime takeOffTime;
    private LocalDateTime arrivalTime;
    @DocumentReference
    private Airport from;
    @DocumentReference
    private Airport to;
    @DocumentReference
    private Airplane airplane;
    @Override
    public int hashCode() {
        return 2024;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return id != null && id.equals(((Schedule) obj).id);
    }


}
