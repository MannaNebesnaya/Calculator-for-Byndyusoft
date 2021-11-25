package com.byndyusoft.util;

import com.byndyusoft.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MathUtilTest extends AbstractTest {

    @Test
    void isNumeric() {
        String actual = "220";
        boolean isNumeric = MathUtil.isNumeric(actual);
        assertThat(isNumeric).isTrue();

    }

    @Test
    void isNotNumeric() {
        String actual = "isNotNumeric";
        boolean isNumeric = MathUtil.isNumeric(actual);
        assertThat(isNumeric).isFalse();
    }

    @Test
    void getResultArithmeticOperation() {
        double actual = MathUtil.getResultArithmeticOperation(5, 10, "*");
        double expected = 50.0;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getResultArithmeticOperationWithUnsupportedOperator() {
        assertThrows(IllegalArgumentException.class, () ->
                MathUtil.getResultArithmeticOperation(5, 10, "^^^"));
    }
}