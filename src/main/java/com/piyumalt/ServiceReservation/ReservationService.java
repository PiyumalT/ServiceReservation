package com.piyumalt.ServiceReservation;

import com.piyumalt.ServiceReservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    List<Reservation> getAllServiceRecords() {
        return reservationRepo.findAll();
    }
    public Reservation getServiceRecordById(int id) {
        return reservationRepo.findById((long) id).get();
    }

    boolean addServiceRecord(Reservation reservation) {
        Reservation newReservation = reservationRepo.save(reservation);
        if (getServiceRecordById((int) newReservation.getId()) != null) {
            return true;
        }
        else{
            return false;
        }
    }

    boolean deleteServiceRecord(int id) {
        reservationRepo.deleteById((long) id);
        if (getServiceRecordById(id) == null) {
            return true;
        }
        else{
            return false;
        }
    }


}
