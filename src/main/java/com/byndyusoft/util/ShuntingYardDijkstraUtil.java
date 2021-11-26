package com.byndyusoft.util;

import java.util.ArrayList;
import java.util.List;

import static com.byndyusoft.util.MathUtil.isNumeric;

final public class ShuntingYardDijkstraUtil {

    private ShuntingYardDijkstraUtil() {
    }


    public static String syntaxCorrection(String expression) {
        return expression
                .replaceAll(",", ".")
                .replaceAll("·", "*")
                .replaceAll("–", "-")
                .replaceAll("[^0-9[+\\-*/.()]]", "");
    }

    public static List<String> splitOnLexemes (String expression) {
        List<String> stack = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (String value : expression.split("")) {
            if (isNumeric(value) || value.equals(".")) {
                number.append(value);
            } else {
                if (!number.toString().isEmpty()) {
                    stack.add(number.toString());
                }
                stack.add(value);
                number.setLength(0);
            }
        }
        return stack;
    }
}
