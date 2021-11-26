package com.byndyusoft.calculator;

import com.byndyusoft.util.exception.IncorrectFormatExpression;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import static com.byndyusoft.util.MathUtil.isNumeric;
import static com.byndyusoft.util.ShuntingYardDijkstraUtil.splitOnLexemes;
import static com.byndyusoft.util.ShuntingYardDijkstraUtil.syntaxCorrection;


final public class ShuntingYardDijkstra {
    private static final ConcurrentHashMap<String, Integer> operatorsPriority = new ConcurrentHashMap<>();

    static {
        operatorsPriority.put("+", 1);
        operatorsPriority.put("-", 1);
        operatorsPriority.put("*", 2);
        operatorsPriority.put("/", 2);
        operatorsPriority.put("(", 10);
        operatorsPriority.put(")", 10);

    }

    private ShuntingYardDijkstra() {
    }

    public static List<String> getReversePolishNotation(String expression) {
        expression = syntaxCorrection(expression);
        List<String> lexemes = splitOnLexemes(expression);

        return sort(lexemes);
    }

    /**
     *  Sorting the specified lexemes using Shunting-yard algorithm.
     *  Not numeric lexemes are located in priority order.
     *
     *  <p>For Example:
     *  <p>input: "(", "2.5", "+", "3.5", ")", "/", "5", "+", "1", "*", "(", "6", "+", "8", ")"
     *  <p>output: "2.5", "3.5", "+", "5", "/", "1", "6", "8", "+", "*", "+"
     * @param lexemes sequence of the lexemes
     * @return the list sequence of the lexemes in the form Reverse Polish notation
     */
    private static List<String> sort(List<String> lexemes) {
        LinkedList<String> outString = new LinkedList<>();
        LinkedList<String> stack = new LinkedList<>();

        for (String value : lexemes) {
            if (isNumeric(value)) {
                outString.add(value);
            } else {
                if (stack.isEmpty() || value.equals("(")) {
                    stack.add(value);
                    continue;
                }
                if (value.equals(")")) {
                    String temp;
                    try {
                        while (!(temp = stack.removeLast()).equals("(")) {
                            outString.add(temp);
                        }
                        continue;
                    }catch (NoSuchElementException e) {
                        throw new IncorrectFormatExpression("Parentheses put not true");
                    }
                }
                if (getPriority(value) > getPriority(stack.getLast())) {
                    stack.add(value);
                    continue;
                }
                if (getPriority(value) <= getPriority(stack.getLast())) {
                    while (!stack.isEmpty()
                            && !stack.getLast().equals("(")
                            && getPriority(value) <= getPriority(stack.getLast())) {
                        outString.add(stack.removeLast());
                    }
                    stack.add(value);
                }
            }
        }
        while (!stack.isEmpty()) outString.add(stack.removeLast());

        return outString;
    }

    private static int getPriority(String operator) throws NullPointerException {
        try {
            return operatorsPriority.get(operator);
        }catch (NullPointerException e) {
            throw new IllegalArgumentException("The operator is not supported: " + operator);
        }
    }
}
