package org.woridge.settings_management.utils;

import org.woridge.settings_management.exception.file.MissingFileExtensionException;

import java.util.UUID;

public final class FileUtils {

    private FileUtils() {

        throw new UnsupportedOperationException("You can't instantiate file utils object");
    }

    // Format : prefix_uuid.extension
    public static String buildFileName(String prefix, String extension) {

        return prefix.toLowerCase() + "_" + UUID.randomUUID() + extension;
    }

    public static String extractExtension(String originalFilename) {

        if (originalFilename != null && originalFilename.contains(".")) {

            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        throw new MissingFileExtensionException("File : " + originalFilename + " has no extension");
    }
}
