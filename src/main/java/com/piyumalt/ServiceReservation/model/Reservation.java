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
    private int millage;

    @Column
    private String message;

    @Column
    private String username;

    public Reservation() {
    }

    public long getId() {
        return id;
    }
}
