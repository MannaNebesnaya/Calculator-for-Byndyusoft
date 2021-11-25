package com.byndyusoft.util;

public class StringMathExpressionFormatter {

    private StringMathExpressionFormatter() {
    }


    public static String formatter(String expression) {
        return expression
                .replaceAll(",", ".")
                .replaceAll("·", "*")
                .replaceAll("–", "-")
                .replaceAll("[^0-9[+\\-*/.()]]", "");
    }
}
