package org.kadirov.util;

public final class PageUtil {

    private PageUtil() { }

    public static int clamp(int value, int min, int max) {
        int max1 = Math.max(min, Math.min(max, value));
        return max1;
    }
}
