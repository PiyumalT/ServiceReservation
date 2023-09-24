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

    @GetMapping("/AddReservations")
    public String addServiceRecord(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "AddReservations";
    }

    @PostMapping("/AddReservations")
    public String addServiceRecord(@ModelAttribute("reservation") Reservation reservation, RedirectAttributes redirectAttributes) {
        if (reservationService.addServiceRecord(reservation)) {
            return "redirect:/view-reservation-details?message=Service Record Added Successfully";
        }
        else{
            return "redirect:/AddReservations?message=Service Record Not Added";
        }
    }

    @GetMapping("/view-reservation-details")
    public String viewReservationDetails(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("serviceRecords", reservationService.getAllServiceRecords());
        model.addAttribute("message", message);
        return "view-reservation-details";
    }

    @DeleteMapping("/deleteServiceRecord/{id}")
       public String deleteServiceRecord(@PathVariable int id, RedirectAttributes redirectAttributes) {
            if (reservationService.deleteServiceRecord(id)) {
                return "redirect:/serviceRecords?message=Service Record Deleted Successfully";
            }
            else{
                return "redirect:/serviceRecords?message=Service Record Not Deleted";
            }
        }
}
//need top add more