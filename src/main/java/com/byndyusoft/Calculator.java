package com.byndyusoft;


import com.byndyusoft.util.exception.IncorrectFormatExpression;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.byndyusoft.util.MathUtil.isNumeric;

/**
 * The {@code Calculator} class contains a method for calculation expression in format String.
 *
 * <p> <strong>Note that this implementation is not synchronize</strong>
 */
@Component
public final class Calculator {
    private static final ConcurrentHashMap<String, Integer> priority = new ConcurrentHashMap<>();

    static {
        priority.put("+", 0);
        priority.put("-", 0);
        priority.put("*", 1);
        priority.put("/", 1);
        priority.put("(", 9);
        priority.put(")", 9);
    }

    /**
     * <p>The expression may contain whole and decimal numbers,
     * signs: "+", "-", "*", "/", "." and brackets: "(", ")".</p>
     *
     * @param expression for calculation
     * @return the result expression
     * @throws IncorrectFormatExpression if the expression contains unsupported
     *                                   signs or has an incorrect format
     */
    public Double calculator(String expression) throws IncorrectFormatExpression {
//        return calculateExpression(convertToList(expression));
        return 0.0;
    }

    private static double calculateExpression(List<String> expression) {
        LinkedList<String> operands = new LinkedList<>();
        LinkedList<String> functions = new LinkedList<>();

        for (String value : expression) {
            if (isNumeric(value)) {
                operands.add(value);
            } else {
                if (value.equals(")")) {
                    while (!functions.getLast().equals("(")) {
                        operands.add(functions.removeLast());
                        pushFunctions(operands);
                    }
                    functions.removeLast();
                    continue;
                }
                if (!functions.isEmpty()) {
                    boolean isOpenBracket = functions.getLast().equals("(");

                    if (priority.get(value).equals(priority.get(functions.getLast())) && !isOpenBracket) {
                        operands.add(functions.removeLast());
                        pushFunctions(operands);
                    } else {
                        if (priority.get(value) < priority.get(functions.getLast()) && !isOpenBracket) {
                            while (!functions.isEmpty()) {
                                operands.add(functions.removeLast());
                                pushFunctions(operands);
                            }
                        }
                    }
                }
                functions.add(value);
            }
        }
        operands.addAll(functions);
        pushFunctions(operands);

        return Double.parseDouble(operands.get(0));
    }

    private static void pushFunctions(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!isNumeric(list.get(i))) {
                String A = list.remove(i - 2);
                String B = list.remove(i - 2);
                String operator = list.get(i - 2);
//                list.set(i - 2, MathFunctions(A, B, operator));
                i = 0;
            }
        }
    }


}