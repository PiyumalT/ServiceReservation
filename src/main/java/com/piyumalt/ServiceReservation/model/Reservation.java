package com.piyumalt.ServiceReservation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "serviceRecord")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String Name;

    @Column
    private String email;

    @Column
    private String password;

    public Reservation() {
    }

    public long getId() {
        return id;
    }
}
