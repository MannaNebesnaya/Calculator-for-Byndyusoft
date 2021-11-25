package com.byndyusoft.util;

import java.util.concurrent.ConcurrentHashMap;

public class MathUtil {
    public static final ConcurrentHashMap<String, Integer> operatorsPriority = new ConcurrentHashMap<>();

    static {
        operatorsPriority.put("+", 0);
        operatorsPriority.put("-", 0);
        operatorsPriority.put("*", 1);
        operatorsPriority.put("/", 1);
        operatorsPriority.put("(", 9);
        operatorsPriority.put(")", 9);
    }

    private MathUtil() {
    }

    public static double getResultArithmeticOperation(double a, double b, String operator) throws IllegalArgumentException {
        double result;

        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("The operator is not supported: " + operator);
        }
        return result;
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
