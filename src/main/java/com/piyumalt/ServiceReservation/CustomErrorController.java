package com.piyumalt.ServiceReservation;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Get the status code and error message from the request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");

        // Add status code and error message as model attributes
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);

        // Provide the path to your custom error page template
        return "errorPage";
    }
}

