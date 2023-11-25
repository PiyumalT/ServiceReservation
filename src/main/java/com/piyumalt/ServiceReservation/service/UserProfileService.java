package com.piyumalt.ServiceReservation.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.piyumalt.ServiceReservation.model.UserProfile;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    public UserProfile getUserProfile(OAuth2AuthenticationToken authenticationToken) {
        // Sanitize user-related variables using TextSanitizer
        String sanitizedUsername = getAttributeAsString(authenticationToken, "username");
        String sanitizedName = getAttributeAsString(authenticationToken, "name");
        String sanitizedEmail = getAttributeAsString(authenticationToken, "email");
        String sanitizedPhoneNumber = getAttributeAsString(authenticationToken, "phone_number");

        JSONObject addressJsonObj = authenticationToken.getPrincipal().getAttribute("address");
        String sanitizedCountry = getCountryAttributeAsString(addressJsonObj);

        return new UserProfile(sanitizedUsername, sanitizedName, sanitizedEmail, sanitizedPhoneNumber, sanitizedCountry);
    }

    private String getAttributeAsString(OAuth2AuthenticationToken authenticationToken, String attributeName) {
        Object attributeValue = authenticationToken.getPrincipal().getAttribute(attributeName);
        return (attributeValue != null) ? TextSanitizer.sanitizeText(attributeValue.toString()) : null;
    }

    private String getCountryAttributeAsString(JSONObject addressJsonObj) {
        if (addressJsonObj != null) {
            Object countryAttributeValue = addressJsonObj.get("country");
            return (countryAttributeValue != null) ? TextSanitizer.sanitizeText(countryAttributeValue.toString()) : null;
        }
        return null;
    }
}
