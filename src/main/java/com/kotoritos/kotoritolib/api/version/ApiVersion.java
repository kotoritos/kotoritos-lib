package com.kotoritos.kotoritolib.api.version;

/**
 * Semantic API version helpers for dependent mods.
 */
public final class ApiVersion {
    public static final int MAJOR = 1;
    public static final int MINOR = 0;
    public static final int PATCH = 0;

    private ApiVersion() {
    }

    /**
     * @return API version string in semantic version format.
     */
    public static String current() {
        return MAJOR + "." + MINOR + "." + PATCH;
    }

    /**
     * Checks if a requested major version is compatible with the current API.
     *
     * @param expectedMajor major version expected by a dependent mod.
     * @return true if majors match.
     */
    public static boolean isMajorCompatible(int expectedMajor) {
        return expectedMajor == MAJOR;
    }
}
