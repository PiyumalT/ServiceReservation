package com.piyumalt.ServiceReservation.service;

import com.piyumalt.ServiceReservation.model.Reservation;
import com.piyumalt.ServiceReservation.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;


    public List<Reservation> getFutureServiceRecords(OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication);
        LocalDate currentDate = LocalDate.now();

        List<Reservation> reservations = reservationRepo.findByUsername(username);

        // Filter reservations where date is after the current date
        List<Reservation> futureReservations = reservations.stream()
                .filter(reservation -> reservation.getDate().toLocalDate().compareTo(currentDate) > 0)
                .collect(Collectors.toList());

        // Sanitize the list of reservations directly
        sanitizeReservations(futureReservations);

        return futureReservations;
    }


    public List<Reservation> getPastServiceRecords(OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication);
        LocalDate currentDate = LocalDate.now();

        List<Reservation> reservations = reservationRepo.findByUsername(username);

        // Filter reservations where date is before or equal to the current date

        List<Reservation> pastReservations =  reservations.stream()
                .filter(reservation -> reservation.getDate().toLocalDate().compareTo(currentDate) <= 0)
                .collect(Collectors.toList());
        sanitizeReservations(pastReservations);
        return pastReservations;
    }

    public Reservation getServiceRecordById(long id, OAuth2AuthenticationToken authentication) {
        Optional<Reservation> optionalReservation = reservationRepo.findById(id);
        String tokenUsername = getUsernameFromToken(authentication);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Check if the reservation's username matches the token username
            if (reservation.getUsername() == null) {
                return null;
            } else if (reservation.getUsername().equals(tokenUsername)) {
                reservation.setLocation(TextSanitizer.sanitizeText(reservation.getLocation()));
                reservation.setMessage(TextSanitizer.sanitizeText(reservation.getMessage()));
                reservation.setVehicle_no(TextSanitizer.sanitizeText(reservation.getVehicle_no()));
                return reservation;
            } else {
                // Unauthorized access: Reservation username doesn't match token username
                System.out.println("Unauthorized access detected. User" + tokenUsername + " attempted to access reservation with ID: " + id);
                return null;
            }
        } else {
            // Reservation with the given ID not found
            System.out.println("Unauthorized access detected. User" + tokenUsername + " attempted to access reservation with ID: " + id);
            return null;
        }
    }


    public long addServiceRecord(Reservation reservation, OAuth2AuthenticationToken authentication) {
        // Validate reservation data
        String username = getUsernameFromToken(authentication);
        if (!isValidReservation(reservation)) {
            System.out.println("Invalid reservation data detected. From user: " + username);
            return 0;
        }
        // Add the username to the reservation object
        if (username == null) {
            System.out.println("Invalid username");
            return 0;
        } else {
            reservation.setUsername(username);
        }
        // Save reservation to the database
        Reservation newReservation = reservationRepo.save(reservation);
        return newReservation.getBooking_id();
    }


    public boolean deleteServiceRecord(long id, OAuth2AuthenticationToken authentication) {
        String username = getUsernameFromToken(authentication);

        Optional<Reservation> optionalReservation = reservationRepo.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            // Get today's date
            LocalDate currentDate = LocalDate.now();
            LocalDate reservationDate = reservation.getDate().toLocalDate();

            // Check if the reservation's date is in the future
            if (reservation.getUsername().equals(username) && reservationDate.compareTo(currentDate) > 0) {
                reservationRepo.deleteById(id);

                // Check if the reservation is deleted successfully
                return !(reservationRepo.findById(id).isPresent());
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
        List<Time> validTimes = LocationAndTimeService.convertToTimeList();// Get times as Time objects

        List<String> validTimeStrings = new ArrayList<>();
        for (Time validTime : validTimes) {
            // Format Time objects to strings without seconds
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = timeFormat.format(validTime);
            validTimeStrings.add(formattedTime);
        }

        List<String> validLocations = LocationAndTimeService.getLocations();

        List<String> errors = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate reservationDate = reservation.getDate().toLocalDate();

        // Check if the reservation's date is in the future
        if (currentDate.compareTo(reservationDate) > 0) {
            errors.add("Reservation date must be in the future.");
        }

        // Format reservation time to exclude seconds for comparison
        String reservationTimeFormatted = reservation.getTime().substring(0, 5);

        if (!validTimeStrings.contains(reservationTimeFormatted)) {
            errors.add(reservation.getTime() + " is not a valid time.");
            errors.add("Valid times are: " + validTimeStrings);
        }

        if (!validLocations.contains(reservation.getLocation())) {
            errors.add("Invalid location selected.");
        }
        if (reservation.getMileage() < 0) {
            errors.add("Mileage must be greater than 0.");
        }

// Sanitize vehicle number using TextSanitizer
        String sanitizedVehicleNumber = TextSanitizer.sanitizeText(reservation.getVehicle_no());

// Set the sanitized vehicle number back to the reservation object
        reservation.setVehicle_no(sanitizedVehicleNumber);

// Check sanitized vehicle number length
        if (sanitizedVehicleNumber.length() < 5 || sanitizedVehicleNumber.length() > 10) {
            errors.add("Sanitized vehicle number must be between 5 and 10 characters.");
        }

        // Sanitize message using TextSanitizer
        String sanitizedMessage = TextSanitizer.sanitizeText(reservation.getMessage());
        reservation.setMessage(sanitizedMessage);


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
            username = (String) authenticationToken.getPrincipal().getAttributes().get("username");
        }
        return username;
    }


    public boolean isExpired(Reservation reservation) {
        LocalDate currentDate = LocalDate.now();
        LocalDate reservationDate = reservation.getDate().toLocalDate();

        // Check if the reservation's date is in the future
        return reservationDate.compareTo(currentDate) <= 0;
    }

    public void sanitizeReservations(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            // Sanitize text fields directly in the existing list
            reservation.setLocation(TextSanitizer.sanitizeText(reservation.getLocation()));
            reservation.setMessage(TextSanitizer.sanitizeText(reservation.getMessage()));
            reservation.setVehicle_no(TextSanitizer.sanitizeText(reservation.getVehicle_no()));

        }
    }

}
