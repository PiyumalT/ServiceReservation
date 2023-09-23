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

    @GetMapping("/serviceRecords")
    public String getAllServiceRecords(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("serviceRecords", reservationService.getAllServiceRecords());
        model.addAttribute("message", message);
        return "serviceRecords";
    }

    @GetMapping("/addServiceRecord")
    public String addServiceRecord(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "addServiceRecord";
    }

    @PostMapping("/addServiceRecord")
    public String addServiceRecord(Reservation reservation, RedirectAttributes redirectAttributes) {
        if (reservationService.addServiceRecord(reservation)) {
            return "redirect:/serviceRecords?message=Service Record Added Successfully";
        }
        else{
            return "redirect:/serviceRecords?message=Service Record Not Added";
        }

    }
    @DeleteMapping("/deleteServiceRecord/{id}")
       public String deleteServiceRecord(@PathVariable long id, RedirectAttributes redirectAttributes) {
            if (reservationService.deleteServiceRecord(id)) {
                return "redirect:/serviceRecords?message=Service Record Deleted Successfully";
            }
            else{
                return "redirect:/serviceRecords?message=Service Record Not Deleted";
            }
        }
}
//need top add more