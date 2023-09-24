package com.piyumalt.ServiceReservation.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = " vehicle_service")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;

    @Column
    private String time;

    @Column
    private String location;

    @Column
    private String vehicle_no;

    @Column
    private int mileage;

    @Column
    private String message;

    @Column
    private String username;

    public Reservation() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
    public String getVehicle_no() {
        return vehicle_no;
    }
    public int getMileage() {
        return mileage;
    }
    public String getMessage() {
        return message;
    }
    public String getUsername() {
        return username;
    }
}
