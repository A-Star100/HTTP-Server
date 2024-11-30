package com.google.appinventor.common.version;

public final class GitBuildId {
    public static final String ACRA_URI = "${acra.uri}";
    public static final String ANT_BUILD_DATE = "November 17 2024";
    public static final String GIT_BUILD_FINGERPRINT = "a35187a6ee82e103dbc7f9cc653fb478d108e45e";
    public static final String GIT_BUILD_VERSION = "nb199";

    private GitBuildId() {
    }

    public static String getVersion() {
        if (GIT_BUILD_VERSION == "" || GIT_BUILD_VERSION.contains(" ")) {
            return "none";
        }
        return GIT_BUILD_VERSION;
    }

    public static String getFingerprint() {
        return GIT_BUILD_FINGERPRINT;
    }

    public static String getDate() {
        return ANT_BUILD_DATE;
    }

    public static String getAcraUri() {
        if (ACRA_URI.equals(ACRA_URI)) {
            return "";
        }
        return ACRA_URI.trim();
    }
}
