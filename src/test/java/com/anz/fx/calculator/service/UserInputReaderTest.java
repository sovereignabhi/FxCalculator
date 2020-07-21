package com.anz.fx.calculator.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserInputReaderTest {

    private UserInputReader inputReader = new UserInputReader();

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "abc to def", "aud 100 in usd etc etc", "AUD xyz in USD"})
    void testReadUserInputInvalidValues(String input) {
        assertThrows(Exception.class, () -> inputReader.getFormattedInput(input), "Invalid");
    }

    @ParameterizedTest
    @MethodSource("readUserInputValuesScenarios")
    void testReadUserInput(String input, List<String> expected) throws Exception {
        assertEquals(expected, inputReader.getFormattedInput(input));
    }

    private static Stream<Arguments> readUserInputValuesScenarios() {
        return Stream.of(
                Arguments.of("AUD 100.00 in USD", List.of("AUD", "USD", "100.00")),
                Arguments.of("JPY 1000.000 to NZD", List.of("JPY", "NZD", "1000.000"))
        );
    }
}