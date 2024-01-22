package com.twoteethreeeight.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeAuthenticate implements Serializable {
    private String code;
    private LocalDateTime expiration;
}
