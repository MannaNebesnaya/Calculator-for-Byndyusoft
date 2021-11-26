package com.byndyusoft.calculator;


import com.byndyusoft.AbstractTest;
import com.byndyusoft.calculator.CalculatorStringMathExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


class CalculatorStringMathExpressionTest extends AbstractTest {

    @Autowired
    private CalculatorStringMathExpression calculatorStringMathExpression;

    @Test
    void calculator() {
        String expression = "(2.5+3.5)/6+1*(6+8)";
        Double expected = 15.0;
        Double actual = calculatorStringMathExpression.calculate(expression);
        assertThat(actual).isEqualTo(expected);
    }

}