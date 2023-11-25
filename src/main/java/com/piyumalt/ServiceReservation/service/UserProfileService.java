package com.piyumalt.ServiceReservation.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.piyumalt.ServiceReservation.model.UserProfile;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    public UserProfile getUserProfile(OAuth2AuthenticationToken authenticationToken) {
// Sanitize user-related variables using TextSanitizer
        String sanitizedUsername = TextSanitizer.sanitizeText(authenticationToken.getPrincipal().getAttribute("username"));
        String sanitizedName = TextSanitizer.sanitizeText(authenticationToken.getPrincipal().getAttribute("name"));
        String sanitizedEmail = TextSanitizer.sanitizeText(authenticationToken.getPrincipal().getAttribute("email"));
        String sanitizedPhoneNumber = TextSanitizer.sanitizeText(authenticationToken.getPrincipal().getAttribute("phone_number"));

        JSONObject addressJsonObj = authenticationToken.getPrincipal().getAttribute("address");
        String sanitizedCountry = TextSanitizer.sanitizeText(addressJsonObj.get("country").toString());

        return new UserProfile(sanitizedUsername, sanitizedName, sanitizedEmail, sanitizedPhoneNumber, sanitizedCountry);
    }
}
