package com.piyumalt.ServiceReservation.service;

import com.piyumalt.ServiceReservation.model.Reservation;
import com.piyumalt.ServiceReservation.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

//    List<Reservation> getAllServiceRecords(OAuth2AuthenticationToken authentication) {
//        String username = getUsernameFromToken(authentication);
//        List<Reservation> reservations = reservationRepo.findByUsername(username);
//
//        // Sort reservations by ID in descending order
//        List<Reservation> sortedReservations = reservations.stream()
//                .sorted((r1, r2) -> Long.compare(r2.getId(), r1.getId()))
//                .collect(Collectors.toList());
//
//        return sortedReservations;
//    }


    public List<Reservation> getFutureServiceRecords(OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication);
        LocalDate currentDate = LocalDate.now();

        List<Reservation> reservations = reservationRepo.findByUsername(username);

        // Filter reservations where date is after the current date

        return reservations.stream()
                .filter(reservation -> LocalDate.parse(reservation.getDate()).compareTo(currentDate) > 0)
                .collect(Collectors.toList());
    }

    public List<Reservation> getPastServiceRecords(OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication);
        LocalDate currentDate = LocalDate.now();

        List<Reservation> reservations = reservationRepo.findByUsername(username);

        // Filter reservations where date is before or equal to the current date

        return reservations.stream()
                .filter(reservation -> LocalDate.parse(reservation.getDate()).compareTo(currentDate) <= 0)
                .collect(Collectors.toList());
    }

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


    public boolean deleteServiceRecord(long id, OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication);

        Optional<Reservation> optionalReservation = reservationRepo.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Get today's date
            LocalDate currentDate = LocalDate.now();
            LocalDate reservationDate = LocalDate.parse(reservation.getDate());

            // Check if the reservation's date is in the future
            if (reservation.getUsername().equals(username) && reservationDate.compareTo(currentDate) > 0) {
                reservationRepo.deleteById(id);

                // Check if the reservation is deleted successfully
                return reservationRepo.findById(id).isEmpty();
            } else if (!reservation.getUsername().equals(username)) {
                // Unauthorized access: Reservation username doesn't match token username
                System.out.println("Unauthorized access detected. User " + username + " attempted to delete reservation with ID: " + id);
                return false;
            } else {
                // Attempted to delete a past reservation
                System.out.println("Unauthorized access detected. User " + username + " attempted to delete past reservation with ID: " + id);
                return false;
            }
        } else {
            // Reservation with the given ID not found
            System.out.println("Unauthorized access detected. User " + username + " attempted to delete non-existent reservation with ID: " + id);
            return false;
        }
    }



    private boolean isValidReservation(Reservation reservation) {
        List<String> validTimes = LocationAndTimeService.getTimes();
        List<String> validLocations = LocationAndTimeService.getLocations();

        List<String> errors = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate reservationDate = LocalDate.parse(reservation.getDate());

        // Check if the reservation's date is in the future
        if (currentDate.compareTo(reservationDate) > 0) {
            errors.add("Reservation date must be in the future.");
        }

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


    public boolean isExpired(Reservation reservationDetails) {
        LocalDate currentDate = LocalDate.now();
        LocalDate reservationDate = LocalDate.parse(reservationDetails.getDate());

        // Check if the reservation's date is in the future
        return reservationDate.compareTo(currentDate) <= 0;
    }
}
