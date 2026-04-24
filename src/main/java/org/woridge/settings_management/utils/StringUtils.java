package org.woridge.settings_management.utils;

public final class StringUtils {

    private StringUtils() {

        throw new UnsupportedOperationException("You can't instantiate string utils object");
    }

    public static String canonicalizeString(String value) {

        if (value == null) {

            throw new IllegalArgumentException("String value can't be null");
        }

        return value
                .trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9-]", "-");
    }
}