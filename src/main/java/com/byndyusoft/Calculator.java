package com.byndyusoft;


import com.byndyusoft.util.exception.IncorrectFormatExpression;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import static com.byndyusoft.util.MathUtil.*;

@Component
public final class Calculator {

    public Double calculator(String expression) throws IncorrectFormatExpression {
//        return calculateExpression(convertToList(expression));
        return 0.0;
    }

    /*private static double calculateExpression(List<String> expression) {
        LinkedList<String> values = new LinkedList<>();
        LinkedList<String> operators = new LinkedList<>();

        for (String value : expression) {
            if (isNumeric(value)) {
                values.add(value);
            } else {
                if (value.equals(")")) {
                    while (!operators.getLast().equals("(")) {
                        values.add(operators.removeLast());
                        pushOperators(values);
                    }
                    operators.removeLast();
                    continue;
                }
                if (!operators.isEmpty()) {
                    boolean isOpenBracket = operators.getLast().equals("(");

                    if (operatorsPriority.get(value).equals(operatorsPriority.get(operators.getLast())) && !isOpenBracket) {
                        values.add(operators.removeLast());
                        pushOperators(values);
                    } else {
                        if (operatorsPriority.get(value) < operatorsPriority.get(operators.getLast()) && !isOpenBracket) {
                            while (!operators.isEmpty()) {
                                values.add(operators.removeLast());
                                pushOperators(values);
                            }
                        }
                    }
                }
                operators.add(value);
            }
        }
        values.addAll(operators);
        pushOperators(values);

        // Если > 1 элемента, то exception
        return Double.parseDouble(values.get(0));
    }*/


    // public - for tests. Must be private
    public void pushOperators(LinkedList<Double> numbers, String[] operators) {
        for (String operator : operators) {
            double b = numbers.removeLast();
            double a = numbers.removeLast();
            double result = getResultArithmeticOperation(a, b, operator);
            numbers.addLast(result);
        }
    }

}