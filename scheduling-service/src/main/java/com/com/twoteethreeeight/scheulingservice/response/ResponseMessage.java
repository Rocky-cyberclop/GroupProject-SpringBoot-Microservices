package com.com.twoteethreeeight.scheulingservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseMessage {
    private String message;
    private String rspCode;
    private String state;
    private Object data;

    public ResponseMessage() {
        message = "";
        rspCode = "200";
        state = "success";
    }
}
