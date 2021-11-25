package com.byndyusoft.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

class StringMathExpressionConverterTest {

    @Test
    void convertToList() {
        List<String> actual = StringMathExpressionConverter.convertToList("2+3-(3.5*2.5)");
        List<String> expected = Arrays.asList("2", "+", "3", "-", "(", "3.5", "*", "2.5", ")");
        assertThat(actual).isEqualTo(expected);
    }
}