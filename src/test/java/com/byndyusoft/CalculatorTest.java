package com.byndyusoft;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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


    @Test
    void pushOperators() {
        LinkedList<Double> numbers = new LinkedList<>(Arrays.asList(2.0, 4.0, 6.0));
        String[] operators = new String[]{"*", "+"};
        LinkedList<Double> expected = new LinkedList<>(List.of(26.0));

        calculator.pushOperators(numbers, operators); // actual result
        assertThat(numbers).isEqualTo(expected);
    }

}