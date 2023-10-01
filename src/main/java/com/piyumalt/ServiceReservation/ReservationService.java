package com.piyumalt.ServiceReservation;

import com.piyumalt.ServiceReservation.model.Reservation;
import com.piyumalt.ServiceReservation.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

    List<Reservation> getAllServiceRecords(OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication) ;
        return reservationRepo.findByUsername(username);
    }

//    public List<Reservation> getFutureServiceRecords() {
//        // Get the current date
//        String currentDateAsString = LocalDate.now().toString();
//        System.out.println("Current Date: " + currentDateAsString);
//        List<Reservation> futureReservations = reservationRepo.findFutureReservations(currentDateAsString, "piyumal");
//
//        return futureReservations;
//    }

    public Reservation getServiceRecordById(long id, OAuth2AuthenticationToken authentication) {
        Optional<Reservation> optionalReservation = reservationRepo.findById(id);
        String tokenUsername = getUsernameFromToken(authentication);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Check if the reservation's username matches the token username
            if (reservation.getUsername()==null){
                return null;
            }
            else if (reservation.getUsername().equals(tokenUsername)) {
                return reservation;
            } else {
                // Unauthorized access: Reservation username doesn't match token username
                System.out.println("Unauthorized access detected. User"+tokenUsername+" attempted to access reservation with ID: " + id);
                return null;
            }
        } else {
            // Reservation with the given ID not found
            System.out.println("Unauthorized access detected. User"+tokenUsername+" attempted to access reservation with ID: " + id);
            return null;
        }
    }



    public long addServiceRecord(Reservation reservation, OAuth2AuthenticationToken authentication) {
        // Validate reservation data
        String username = getUsernameFromToken(authentication) ;
        if (!isValidReservation(reservation)) {
            System.out.println("Invalid reservation data detected. From user: " + username);
            return 0;
        }
        // Add the username to the reservation object
        if (username == null) {
            System.out.println("Invalid username");
            return 0;
        }
        else {
            reservation.setUsername(username);
        }
        // Save reservation to the database
        Reservation newReservation = reservationRepo.save(reservation);
        return newReservation.getId();
    }


    boolean deleteServiceRecord(long id, OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication);

        Optional<Reservation> optionalReservation = reservationRepo.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Check if the reservation's username matches the token username
            if (reservation.getUsername().equals(username)) {
                reservationRepo.deleteById(id);

                // Check if the reservation is deleted successfully
                return reservationRepo.findById(id).isEmpty();
            } else {
                // Unauthorized access: Reservation username doesn't match token username
                System.out.println("Unauthorized access detected. User"+username+" attempted to delete reservation with ID: " + id);
                return false;
            }
        } else {
            // Reservation with the given ID not found
            System.out.println("Unauthorized access detected. User"+username+" attempted to delete not exist reservation with ID: " + id);
            return false;
        }
    }


    private boolean isValidReservation(Reservation reservation) {
        LocalDate today = LocalDate.now();
        List<String> validTimes = Arrays.asList("10 AM", "11 AM", "12 PM");
        List<String> validLocations = Arrays.asList("District 1", "District 2", "District 3");

        List<String> errors = new ArrayList<>();

        if (!validTimes.contains(reservation.getTime())) {
            errors.add("Reservation time must be 10 AM, 11 AM, or 12 PM.");
        }
        if (!validLocations.contains(reservation.getLocation())) {
            errors.add("Invalid location selected.");
        }
        if (reservation.getMileage() < 0) {
            errors.add("Mileage must be greater than 0.");
        }

        if (!errors.isEmpty()) {
            // Print errors
            for (String error : errors) {
                System.out.println(error);
            }
            return false;
        }

        return true;
    }


    private String getUsernameFromToken(OAuth2AuthenticationToken authenticationToken) {
        String username = null;
        if (authenticationToken != null) {
            // Extract username from authentication token
            username = (String) authenticationToken.getPrincipal().getAttributes().get("preferred_username");
        }
        return username;
    }


}
