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
    public Reservation getServiceRecordById(long id) {
        return reservationRepo.findById(id).get();
    }

    boolean addServiceRecord(Reservation reservation) {
        Reservation newReservation = reservationRepo.save(reservation);
        if (getServiceRecordById(newReservation.getId()) != null) {
            return true;
        }
        else{
            return false;
        }
    }

    boolean deleteServiceRecord(long id) {
        reservationRepo.deleteById(id);
        if (getServiceRecordById(id) == null) {
            return true;
        }
        else{
            return false;
        }
    }


}
