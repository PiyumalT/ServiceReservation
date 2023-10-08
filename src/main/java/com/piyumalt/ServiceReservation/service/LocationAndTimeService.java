package com.piyumalt.ServiceReservation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationAndTimeService {

    private static List<String> locations;
    private static List<String> times;

    @Value("#{'${reservation.locations}'.split(',')}") // Inject the comma-separated values as a List
    private List<String> injectedLocations;

    @Value("#{'${reservation.times}'.split(',')}") // Inject the comma-separated values as a List
    private List<String> injectedTimes;

    @PostConstruct
    private void initialize() {
        locations = injectedLocations;
        times = injectedTimes;
    }

    public static List<String> getLocations() {
        return locations;
    }

    public static List<String> getTimes() {
        return times;
    }
    public static List<Time> convertToTimeList() {
        List<String> timeStrings = getTimes();
        List<Time> timeList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh a");

        for (String timeString : timeStrings) {
            try {
                java.util.Date parsedDate = dateFormat.parse(timeString);
                Time time = new Time(parsedDate.getTime());
                timeList.add(time);
            } catch (ParseException e) {
                e.printStackTrace(); // Printing the error message to the console
            }
        }

        return timeList;
    }
}
