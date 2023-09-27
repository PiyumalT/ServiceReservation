package com.piyumalt.ServiceReservation;

import com.piyumalt.ServiceReservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/ViewReservations")
    public String getAllServiceRecords(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("serviceRecords", reservationService.getAllServiceRecords());
        model.addAttribute("message", message);
        return "ViewReservations";
    }
//    futureReservations
    @GetMapping("/futureReservations")
    public String getFutureServiceRecords(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("serviceRecords", reservationService.getFutureServiceRecords());
        model.addAttribute("message", message);
        return "comingReservations";
    }

    @GetMapping("/AddReservations")
    public String addServiceRecord(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "AddReservations";
    }

    @PostMapping("/AddReservations")
    public String addServiceRecord(Model model, @ModelAttribute("reservation") Reservation reservation , RedirectAttributes redirectAttributes) {
        int NewReservationId = reservationService.addServiceRecord(reservation);
        if (NewReservationId != 0) {
//            return "redirect://view-reservation-details/?message=Service Record Added Successfully";
            redirectAttributes.addFlashAttribute("message", "Added");
            return "redirect:/view-reservation-details/" + NewReservationId;
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Failed");
            return "redirect:/AddReservations";
        }
    }
//
//    @GetMapping("/view-reservation-details")
//    public String viewReservationDetails(Model model, @ModelAttribute("message") String message) {
//        model.addAttribute("reservationDetails", reservationService.getServiceRecordById(352));
//        model.addAttribute("message", message);
//        return "view-reservation-details";
//    }

    @GetMapping("/view-reservation-details/{reservationId}")
    public String viewReservationDetails(@PathVariable int reservationId, Model model){
        // Assuming you have a service method to retrieve the reservation details by ID
        Reservation reservationDetails = reservationService.getServiceRecordById(Math.toIntExact(reservationId));

        // Add the reservationDetails and message attributes to the model
        model.addAttribute("reservationDetails", reservationDetails);

        return "view-reservation-details";
    }


    @GetMapping("/deleteServiceRecord/{id}")
       public String deleteServiceRecord(@PathVariable int id, RedirectAttributes redirectAttributes) {
            if (reservationService.deleteServiceRecord(id)) {
                redirectAttributes.addFlashAttribute("message", "Deleted");
            }
            else{
                redirectAttributes.addFlashAttribute("message", "Failed");
            }
        return "redirect:/ViewReservations";
    }
}
//need top add more