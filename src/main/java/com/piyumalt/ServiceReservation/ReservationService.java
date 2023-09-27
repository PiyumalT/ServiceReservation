package com.piyumalt.ServiceReservation;

import com.piyumalt.ServiceReservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    List<Reservation> getAllServiceRecords() {
        return reservationRepo.findAll();
    }

    public List<Reservation> getFutureServiceRecords() {
        // Get the current date
        String currentDateAsString = LocalDate.now().toString();
        System.out.println("Current Date: " + currentDateAsString);
        List<Reservation> futureReservations = reservationRepo.findFutureReservations(currentDateAsString, "piyumal");

        return futureReservations;
    }

    public Reservation getServiceRecordById(int id) {
        return reservationRepo.findById((long) id).get();
    }

    int addServiceRecord(Reservation reservation) {
        Reservation newReservation = reservationRepo.save(reservation);
        if (getServiceRecordById((int) newReservation.getId()) != null) {
            return (int) newReservation.getId();
        }
        else{
            return 0;
        }
    }

    boolean deleteServiceRecord(int id) {
        if (reservationRepo.findById((long) id).isEmpty()) {
            return false;
        }
        reservationRepo.deleteById((long) id);
        return reservationRepo.findById((long) id).isEmpty();
    }


}
