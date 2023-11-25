package com.piyumalt.ServiceReservation.service;

public class TextSanitizer {

    public static String sanitizeText(String Text) {
        // Escape HTML entities
        String escapedText = escapeHtmlEntities(Text);

        // Remove potentially harmful HTML tags
        return (removeHtmlTags(escapedText));

    }

    private static String escapeHtmlEntities(String input) {
        return input
//                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    private static String removeHtmlTags(String input) {
        // Replace or remove any HTML tags as needed
        return input.replaceAll("<.*?>", "");
    }
}
