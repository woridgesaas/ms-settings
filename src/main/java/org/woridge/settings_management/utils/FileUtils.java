package org.woridge.settings_management.utils;

import java.util.UUID;

public final class FileUtils {

    private FileUtils() {

        throw new UnsupportedOperationException("You can't instantiate file utils object");
    }

    // Format : category_uuid.pdf
    public static String buildDocumentTemplateFileName(String category) {

        return category.toLowerCase() + "_" + UUID.randomUUID() + ".pdf";
    }
}
