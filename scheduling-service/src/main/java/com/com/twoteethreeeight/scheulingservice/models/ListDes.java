package com.com.twoteethreeeight.scheulingservice.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListDes {
    private String name;
    private List<String> listDestination;
}
