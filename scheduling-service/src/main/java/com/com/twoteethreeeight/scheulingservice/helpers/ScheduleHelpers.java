package com.com.twoteethreeeight.scheulingservice.helpers;

import com.com.twoteethreeeight.scheulingservice.dto.ResultDateWithIndex;
import com.com.twoteethreeeight.scheulingservice.models.FlightTime;
import com.com.twoteethreeeight.scheulingservice.models.Airport;
import com.com.twoteethreeeight.scheulingservice.models.Runway;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ScheduleHelpers {
    private final static String dateTimePattern = "yyyy-MM-dd HH:mm";

    public Airport calculateLastDestination(int totalAirports, String lastDes,
                                                  String currentAirport, List<Airport> airports) {
        int posInListAirport = 0;

        // not have last destination
        if (lastDes.isEmpty()) {
            if (airports.get(0).getName().equals(currentAirport)) {
                return airports.get(1);
            } else {
                return airports.get(0);
            }
        }

        // find index in array
        for (int i = 0; i < airports.size(); i++) {
            if (airports.get(i).getName().equals(lastDes)) {
                posInListAirport = i;
            }
        }

        // if it over array
        if (posInListAirport + 1 > airports.size() - 1) {
            if (airports.get(0).getName().equals(currentAirport)) {
                return airports.get(1);
            } else {
                return airports.get(0);
            }
        }

        if (airports.get(posInListAirport + 1).getName().equals(currentAirport)) {
            if (posInListAirport + 2 > totalAirports - 1) {
                if (airports.get(0).getName().equals(currentAirport)) {
                    return airports.get(1);
                } else {
                    return airports.get(0);
                }
            } else {
                posInListAirport++;
            }
        }

        return airports.get(posInListAirport + 1);
    }

    public LocalDateTime calculateDate(LocalDateTime currenDate, float ETime) {
        // convert addition hours to minutes
        long minutesToAdd = (long) (ETime * 60);

        LocalDateTime resultDateTime = currenDate.plus(minutesToAdd, ChronoUnit.MINUTES);
        return resultDateTime;
    }

    public float getETime(List<FlightTime> flightTimes, String dp, String des) {
        List<FlightTime> ls = flightTimes.stream().filter(i ->
                i.getFrom().getName().equals(dp) && i.getTo().getName().equals(des)
                        || i.getFrom().getName().equals(des) && i.getTo().getName().equals(dp)
        ).collect(Collectors.toList());
        float result;
        if (ls.isEmpty()) {
            result = 0;
        } else {
            result = ls.get(0).getEstimatedTime();
        }
        return result;
    }

    public LocalDateTime transperStrToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return LocalDateTime.parse(date, formatter);
    }

    public boolean compareTwoDates(LocalDateTime d1, LocalDateTime d2) {
        int diff = d1.compareTo(d2);
        return diff >= 0;
    }

    public ResultDateWithIndex findTheSmallestTime(List<Runway> runwayInfos, LocalDateTime givenDate) {
        return IntStream.range(0, runwayInfos.size())
                .boxed()
                .filter(index -> runwayInfos.get(index).getAvailableTime().isBefore(givenDate) || runwayInfos.get(index).getAvailableTime().isEqual(givenDate))
                .min((index1, index2) -> runwayInfos.get(index1).getAvailableTime().compareTo(runwayInfos.get(index2).getAvailableTime()))
                .map(index -> new ResultDateWithIndex(runwayInfos.get(index).getAvailableTime(), index))
                .orElseGet(() -> {
                    int minIndex = IntStream.range(0, runwayInfos.size())
                            .boxed()
                            .min((index1, index2) -> runwayInfos.get(index1).getAvailableTime().compareTo(runwayInfos.get(index2).getAvailableTime()))
                            .orElse(-1);
                    return new ResultDateWithIndex(runwayInfos.get(minIndex).getAvailableTime(), minIndex);
                });
    }

    public float calculateDifferenceInHours(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        Duration duration = Duration.between(dateTime1, dateTime2);
        long seconds = duration.getSeconds();
        float hours = seconds / 3600.0f;

        return Math.abs(hours); // Trả về giá trị tuyệt đối
    }
}
