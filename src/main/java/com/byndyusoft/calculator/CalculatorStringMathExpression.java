package com.byndyusoft.calculator;

import com.byndyusoft.Calculator;
import com.byndyusoft.util.MathUtil;
import com.byndyusoft.util.exception.IncorrectFormatExpression;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import static com.byndyusoft.util.MathUtil.isNumeric;


@Component
public class CalculatorStringMathExpression implements Calculator {

    public double calculate(String expression) throws IncorrectFormatExpression {
        List<String> notation = ShuntingYardDijkstra.getReversePolishNotation(expression);

        return calculateReversePolishNotation(notation);
    }


    private double calculateReversePolishNotation(List<String> notation) {
        LinkedList<String> stack = new LinkedList<>();

        for (String value : notation) {
            if (!isNumeric(value)) {
                double b = Double.parseDouble(stack.removeLast());
                double a = Double.parseDouble(stack.removeLast());
                double result = MathUtil.getResultArithmeticOperation(a, b, value);
                stack.add(String.valueOf(result));
            } else {
                stack.add(value);
            }
        }
        if (stack.size() > 1) throw new IncorrectFormatExpression("Incorrect expression");
        return Double.parseDouble(stack.getFirst());
    }
}