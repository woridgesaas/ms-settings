package org.woridge.settings_management.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.woridge.settings_management.exception.request.MissingCinInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingRoleInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingSubdomainInRequestHeaderException;
import org.woridge.settings_management.exception.request.MissingSubscriptionStatusInRequestHeaderException;

public final class RequestUtils {

    private RequestUtils() {

        throw new UnsupportedOperationException("You can't instantiate request utils object");
    }

    public static String extractCINFromHeaderRequest(HttpServletRequest httpServletRequest) {

        String CIN = httpServletRequest.getHeader("X-User-CIN");

        if (CIN == null || CIN.isBlank()) {

            throw new MissingCinInRequestHeaderException("Missing CIN in request header");
        }

        return CIN;
    }

    public static String extractRoleFromHeaderRequest(HttpServletRequest httpServletRequest) {

        String role = httpServletRequest.getHeader("X-User-Role");

        if (role == null || role.isBlank()) {

            throw new MissingRoleInRequestHeaderException("Missing role in request header");
        }

        return role;
    }

    public static String extractSubdomainFromHeaderRequest(HttpServletRequest httpServletRequest) {

        String subdomain = httpServletRequest.getHeader("X-Subdomain");

        if (subdomain == null || subdomain.isBlank()) {

            throw new MissingSubdomainInRequestHeaderException("Missing subdomain in request header");
        }

        return subdomain;
    }

    public static String extractSubscriptionStatusFromHeaderRequest(HttpServletRequest httpServletRequest) {

        String subdomain = httpServletRequest.getHeader("X-Tenant-Status");

        if (subdomain == null || subdomain.isBlank()) {

            throw new MissingSubscriptionStatusInRequestHeaderException("Missing subscription status in request header");
        }

        return subdomain;
    }
}