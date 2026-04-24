package org.woridge.settings_management.utils;

public final class MinioUtils {

    private MinioUtils() {

        throw new UnsupportedOperationException("You can't instantiate minio utils object");
    }

    public static String buildBucketName(String tenantName, String serviceIdentifier) {

        tenantName = StringUtils.canonicalizeString(tenantName);

        return tenantName + "-" + serviceIdentifier;
    }
}