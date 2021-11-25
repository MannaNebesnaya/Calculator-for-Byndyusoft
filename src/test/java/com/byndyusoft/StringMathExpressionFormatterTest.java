package com.byndyusoft;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringMathExpressionFormatterTest {

    @Test
    void formatter() {
        String actual = StringMathExpressionFormatter.formatter("2sss + ^3 – (3,5·2.5)");
        String expected = "2+3-(3.5*2.5)";
        assertThat(actual).isEqualTo(expected);
    }
}