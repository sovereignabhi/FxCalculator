package com.anz.fx.calculator.service;

import com.anz.fx.calculator.constant.Currency;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.anz.fx.calculator.constant.Currency.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversionServiceImplTest {
    ConversionService conversionService = new ConversionServiceImpl();

    @ParameterizedTest
    @MethodSource("calculateRateScenariosDirectLookup")
    public void testCalculateRateDirectLookup(Currency baseCcy, Currency termCcy, Double expectedValue) {
        assertEquals(expectedValue, conversionService.calculateRate(baseCcy, termCcy));
    }

    private static Stream<Arguments> calculateRateScenariosDirectLookup() {
        return Stream.of(
                Arguments.of(AUD, AUD, 1d),
                Arguments.of(AUD, USD, 0.8371d),
                Arguments.of(USD, JPY, 119.95d)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateRateScenariosInverseLookup")
    public void testCalculateRateInverseLookup(Currency baseCcy, Currency termCcy, Double expectedValue) {
        assertEquals(expectedValue, conversionService.calculateRate(baseCcy, termCcy));
    }

    private static Stream<Arguments> calculateRateScenariosInverseLookup() {
        return Stream.of(
                Arguments.of(JPY, USD, 1 / 119.95d),
                Arguments.of(USD, AUD, 1 / 0.8371d)
        );
    }

    //Because ccy calculations involve arithmetic operations, the output of calculateRate function is not limited to 2 places after decimal
    //A delta has been added to the test case to account for that.
    // Final amount is rounded eventually using FXCalculatorApplication.formatAmount() method
    @ParameterizedTest
    @MethodSource("calculateRateScenariosCrossoverLookup")
    public void testCalculateRateCrossoverLookup(Currency baseCcy, Currency termCcy, Double expectedValue) {
        assertEquals(expectedValue, conversionService.calculateRate(baseCcy, termCcy), 0.0099999999999d);
    }

    private static Stream<Arguments> calculateRateScenariosCrossoverLookup() {
        return Stream.of(
                Arguments.of(AUD, JPY, 100.41d),
                Arguments.of(CZK, DKK, 0.27d),
                Arguments.of(NZD, DKK, 4.68d)
        );
    }

}