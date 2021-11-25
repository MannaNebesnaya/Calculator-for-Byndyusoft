package com.byndyusoft;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


class CalculatorTest extends AbstractTest {

    @Autowired
    private Calculator calculator;

    @Test
    @Disabled
    void calculator() {
        String expression = "2 + 1 – 6 / (1 + 2)";
        Double expected = 1.0;
        Double actual = calculator.calculator(expression);
        assertThat(actual).isEqualTo(expected);
    }

}