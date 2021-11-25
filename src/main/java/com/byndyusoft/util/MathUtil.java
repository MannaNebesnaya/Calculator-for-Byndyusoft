package com.byndyusoft.util;

public class MathUtil {

    private MathUtil() {
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
