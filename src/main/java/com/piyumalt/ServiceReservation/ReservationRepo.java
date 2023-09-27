package com.piyumalt.ServiceReservation;

import com.piyumalt.ServiceReservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    // Future reservations
    @Query("SELECT r FROM Reservation r WHERE r.date > :currentDateAsString AND r.username = :username")
    List<Reservation> findFutureReservations(
            @Param("currentDateAsString") String currentDateAsString,
            @Param("username") String username
    );

    // Past reservations and current day reservations
    @Query("SELECT r FROM Reservation r WHERE r.date <= :currentDateAsString AND r.username = :username")
    List<Reservation> findPastReservations(
            @Param("currentDateAsString") String currentDateAsString,
            @Param("username") String username
    );

    @Query("SELECT r FROM Reservation r WHERE r.username = :username")
    List<Reservation> findReservationsByUsername(@Param("username") String username);
}


