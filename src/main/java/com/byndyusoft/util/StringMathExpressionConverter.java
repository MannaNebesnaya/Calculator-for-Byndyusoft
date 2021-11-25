package com.byndyusoft.util;

import java.util.ArrayList;
import java.util.List;

import static com.byndyusoft.util.MathUtil.isNumeric;

public class StringMathExpressionConverter {

    private StringMathExpressionConverter() {
    }

    public static List<String> convertToList(String expression) {

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
