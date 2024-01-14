package com.com.twoteethreeeight.scheulingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Runway implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private LocalDateTime availableTime;

    @Override
    public int hashCode() {
        return 2023;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return name != null && name.equals(((Runway) obj).name);
    }
}
