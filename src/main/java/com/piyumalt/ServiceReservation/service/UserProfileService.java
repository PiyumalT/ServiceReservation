package com.piyumalt.ServiceReservation.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.piyumalt.ServiceReservation.model.UserProfile;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    public UserProfile getUserProfile(OAuth2AuthenticationToken authenticationToken) {
        String username =  authenticationToken.getPrincipal().getAttribute("username");
        String name =  authenticationToken.getPrincipal().getAttribute("name");
        String email = authenticationToken.getPrincipal().getAttribute("email");
        String phoneNumber =  authenticationToken.getPrincipal().getAttribute("phone_number");

        JSONObject addressJsonObj = (JSONObject) authenticationToken.getPrincipal().getAttribute("address");

        String country = addressJsonObj.get("country").toString();

        return new UserProfile(username, name, email, phoneNumber, country);
    }
}
