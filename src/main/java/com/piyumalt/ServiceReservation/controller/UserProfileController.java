package com.piyumalt.ServiceReservation.controller;

import com.piyumalt.ServiceReservation.model.UserProfile;
import com.piyumalt.ServiceReservation.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/view-profile")
    public String profile(OAuth2AuthenticationToken authenticationToken, Model model) {
        System.out.println("Authentication token: " + authenticationToken);
        UserProfile userProfile = userProfileService.getUserProfile(authenticationToken);

        // Add UserProfile object to the model
        model.addAttribute("userProfile", userProfile);

        return "view-profile";
    }
}
