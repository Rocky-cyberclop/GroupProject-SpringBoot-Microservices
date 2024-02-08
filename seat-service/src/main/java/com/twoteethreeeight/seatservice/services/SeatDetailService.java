package com.twoteethreeeight.seatservice.services;

import com.twoteethreeeight.seatservice.models.SeatDetail;

import java.util.List;

public interface SeatDetailService {
    //This function for testing prupose and can be removed with no erro
    public void generateTestSeatDetail();
    //This function for testing prupose and can be removed with no erro
    public void updateSeatDetail();
    public List<SeatDetail> getAll();
}
