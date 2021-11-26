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
    // Приводим к виду ОПЗ (Обратной польской записи). Возвращает математическое выражение в виде ОПЗ
    private static List<String> sort(List<String> lexemes) {
        LinkedList<String> outString = new LinkedList<>();
        LinkedList<String> stack = new LinkedList<>();

        for (String value : lexemes) {
            // Если число, то добавляется в выходную строку
            if (isNumeric(value)) {
                outString.add(value);
            } else {
                // Если стек пустой или это '(', то кладём в стек
                if (stack.isEmpty() || value.equals("(")) {
                    stack.add(value);
                    continue;
                }
                // Выталкиваем из стека все знаки до '('. '()' уничтожаются, не попадая в выходную строку
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
                // Если value имеет больший приоритет, чем последний знак в стеке, то он просто кладётся в стек
                if (getPriority(value) > getPriority(stack.getLast())) {
                    stack.add(value);
                    continue;
                }
                // Если value имеет меньший или равный(вот тут не уверен) приоритет, чем последний знак в стеке,
                // то он выталкивает элементы из стека, пока имеет равный или меньший приоритет. После чего кладётся в стек
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
        // Передаём в выходную строку оставшиеся символы в стеке
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
