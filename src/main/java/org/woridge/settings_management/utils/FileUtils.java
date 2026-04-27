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

    public static String resolveContentType(String filePath) {

        if (filePath == null || filePath.isBlank()) {
            return "application/octet-stream";
        }

        String extension = filePath.substring(filePath.lastIndexOf('.')).toLowerCase();

        return switch (extension) {
            case ".png" -> "image/png";
            case ".jpg", ".jpeg" -> "image/jpeg";
            case ".webp" -> "image/webp";
            case ".svg" -> "image/svg+xml";
            default -> "application/octet-stream";
        };
    }
}
