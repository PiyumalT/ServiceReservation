package com.piyumalt.ServiceReservation.controller;

import com.piyumalt.ServiceReservation.service.LocationAndTimeService;
import com.piyumalt.ServiceReservation.service.ReservationService;
import com.piyumalt.ServiceReservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(OAuth2AuthenticationToken authenticationToken, Model model) {
//        System.out.println("Authentication token: " + authenticationToken);
        String name = (String) authenticationToken.getPrincipal().getAttributes().get("name");
        if (name == null) {
            name =  "User";
        }
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/ViewReservations")
    public String getAllServiceRecords(Model model, @ModelAttribute("message") String message, OAuth2AuthenticationToken authenticationToken) {
        model.addAttribute("pastServiceRecords", reservationService.getPastServiceRecords(authenticationToken));
        model.addAttribute("futureServiceRecords", reservationService.getFutureServiceRecords(authenticationToken));
        model.addAttribute("message", message);
        return "ViewReservations";
    }
//    futureReservations
    @GetMapping("/futureReservations")
    public String getFutureServiceRecords(Model model, @ModelAttribute("message") String message, OAuth2AuthenticationToken authenticationToken) {
        model.addAttribute("serviceRecords", reservationService.getFutureServiceRecords(authenticationToken));
        model.addAttribute("message", message);
        return "comingReservations";
    }

    @GetMapping("/AddReservations")
    public String addServiceRecord(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("Locations", LocationAndTimeService.getLocations());
        model.addAttribute("Times", LocationAndTimeService.getTimes());
        return "AddReservations";
    }

    @GetMapping("/AddReservation")
    public String addServiceRecord(Model model, @ModelAttribute("reservation") Reservation reservation , RedirectAttributes redirectAttributes, OAuth2AuthenticationToken authenticationToken) {
        long NewReservationId = reservationService.addServiceRecord(reservation , authenticationToken);
        if (NewReservationId != 0) {
            redirectAttributes.addFlashAttribute("message", "Added");
            return "redirect:/view-reservation-details/" + NewReservationId;
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Failed");
            return "redirect:/AddReservations";
        }
    }

    @GetMapping("/view-reservation-details/{reservationId}")
    public String viewReservationDetails(@PathVariable int reservationId, Model model, RedirectAttributes redirectAttributes, OAuth2AuthenticationToken authenticationToken){
        // Assuming you have a service method to retrieve the reservation details by ID
        Reservation reservationDetails = reservationService.getServiceRecordById(Math.toIntExact(reservationId), authenticationToken);

        // Add the reservationDetails and message attributes to the model
        if (reservationDetails == null) {
            redirectAttributes.addFlashAttribute("message", "Reservation not found");
            return "redirect:/ViewReservations";
        }
        model.addAttribute("reservationDetails", reservationDetails);
        model.addAttribute("expired",reservationService.isExpired(reservationDetails) );

        return "view-reservation-details";
    }


    @GetMapping("/deleteServiceRecord/{id}")
       public String deleteServiceRecord(@PathVariable long id, RedirectAttributes redirectAttributes, OAuth2AuthenticationToken authenticationToken) {
            if (reservationService.deleteServiceRecord(id,authenticationToken)) {
                redirectAttributes.addFlashAttribute("message", "Deleted");
            }
            else{
                redirectAttributes.addFlashAttribute("message", "Failed");
            }
        return "redirect:/ViewReservations";
    }
}