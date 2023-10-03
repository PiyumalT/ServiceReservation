package com.piyumalt.ServiceReservation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
}
