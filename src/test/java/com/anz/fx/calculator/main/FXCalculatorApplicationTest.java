package com.anz.fx.calculator.main;

import com.anz.fx.calculator.constant.Currency;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.anz.fx.calculator.constant.Currency.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FXCalculatorApplicationTest {

    FXCalculatorApplication fxCalculatorApplication = new FXCalculatorApplication();

    @ParameterizedTest
    @MethodSource("formatAmountScenarios")
    public void testFormatAmount(Currency termCcy, Double inputAmount, Double conversionRate, String expectedValue) {
        assertEquals(expectedValue, fxCalculatorApplication.formatAmount(termCcy, inputAmount, conversionRate));
    }

    private static Stream<Arguments> formatAmountScenarios() {
        return Stream.of(
                Arguments.of(USD, 100.00d, 0.8371d, "83.71"),
                Arguments.of(AUD, 100000d, 1.2315d, "123150.00"),
                Arguments.of(JPY, 100d, 119.95d, "11995")
        );
    }

}