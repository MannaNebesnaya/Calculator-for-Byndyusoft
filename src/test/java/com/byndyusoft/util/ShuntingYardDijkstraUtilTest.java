package com.byndyusoft.util;

import com.byndyusoft.AbstractTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShuntingYardDijkstraUtilTest extends AbstractTest {

    @Test
    void syntaxCorrection() {
        String actual = ShuntingYardDijkstraUtil.syntaxCorrection("2sss + ^3 – (3,5·2.5)");
        String expected = "2+3-(3.5*2.5)";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitOnLexemes() {
        List<String> actual = ShuntingYardDijkstraUtil.splitOnLexemes("(2.5+3.5)/5+1*(6+8)");
        List<String> expected =
                Arrays.asList("(", "2.5", "+", "3.5", ")", "/", "5", "+", "1", "*", "(", "6", "+", "8", ")");
        assertThat(actual).isEqualTo(expected);
    }
}