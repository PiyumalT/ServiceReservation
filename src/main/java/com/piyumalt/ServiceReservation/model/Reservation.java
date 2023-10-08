package com.piyumalt.ServiceReservation.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@Table(name = " vehicle_service")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use GenerationType.IDENTITY for auto-incremented IDs
    private long booking_id;

    @Column
    @NonNull
    private Date date;

    @Column
    @NonNull
    private Time time;

    @Column
    @NonNull
    private String location;

    @Column
    @NonNull
    private String vehicle_no;

    @Column
    @NonNull
    private int mileage;

    @Column
    private String message;

    @Column
    @NonNull
    private String username;

    public Reservation() {
    }

    public long getBooking_id() {
        return booking_id;
    }

    // Getter for date attribute returning as String
    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return String.valueOf(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getTime() {
        if (time != null) {
            return time.toString();
        }
        return null;
    }

    public void setTime(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh a");
            java.util.Date parsedDate = dateFormat.parse(time);
            this.time = new Time(parsedDate.getTime());
        } catch (ParseException e) {
            // Handle the parsing exception if the provided time is not in the expected format
            // You can throw an exception or handle it according to your requirements
            e.printStackTrace(); // Example: Printing the error message to the console
        }
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
