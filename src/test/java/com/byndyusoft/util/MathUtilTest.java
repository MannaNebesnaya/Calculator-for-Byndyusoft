package com.byndyusoft.util;

import com.byndyusoft.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


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

}