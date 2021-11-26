package com.byndyusoft.calculator;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShuntingYardDijkstraTest {

    @Test
    void getReversePolishNotation() {
        List<String> actual = ShuntingYardDijkstra.getReversePolishNotation("(2.5+3.5)/5+1*(6+8)");
        List<String> expected = Arrays.asList("2.5", "3.5", "+", "5", "/", "1", "6", "8", "+", "*", "+");
        assertThat(actual).isEqualTo(expected);
    }
}
