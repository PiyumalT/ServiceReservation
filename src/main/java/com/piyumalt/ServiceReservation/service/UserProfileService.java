package com.piyumalt.ServiceReservation.service;

import com.piyumalt.ServiceReservation.model.UserProfile;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    public UserProfile getUserProfile(OAuth2AuthenticationToken authenticationToken) {
        String username =  authenticationToken.getPrincipal().getAttribute("preferred_username");
        String name =  authenticationToken.getPrincipal().getAttribute("name");
        String email = authenticationToken.getPrincipal().getAttribute("email");
        String phoneNumber =  authenticationToken.getPrincipal().getAttribute("phone_number");
        String country = authenticationToken.getPrincipal().getAttribute("country");

        return new UserProfile(username, name, email, phoneNumber, country);
    }
}
