package com.twoteethreeeight.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeAuthenticate {
    private String code;
    private LocalDateTime expiration;
}
