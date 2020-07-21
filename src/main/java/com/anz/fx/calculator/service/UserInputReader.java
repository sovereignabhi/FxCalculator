package com.anz.fx.calculator.service;

import com.anz.fx.calculator.constant.Currency;

import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class UserInputReader {
    Scanner scanner = new Scanner(System.in);

    public List<String> readUserInput() throws Exception {
        System.out.println("Please input the required conversion(CCY1 Amt in CCY2) or Enter '0' to exit the Application:");
        String input = scanner.nextLine().toUpperCase();

        if ("0".equals(input)) {
            System.exit(0);
        }

        return getFormattedInput(input);
    }

    protected List getFormattedInput(String input) throws Exception {
        if (input == null || input.isEmpty()) {
            throw new Exception("Input cannot be empty. Try again.");
        }

        String[] inputStr = input.split(" ");
        if(inputStr.length != 4) {
            throw new Exception("Invalid input. Try again.");
        }
        validateAmount(inputStr[1]);
        validateCurrency(inputStr[0], inputStr[3]);

        return List.of(inputStr[0], inputStr[3], inputStr[1]);
    }

    private void validateAmount(String amount) throws Exception {
        try {
            Double.valueOf(amount);
        } catch (Exception e) {
            throw new Exception("Amount is invalid. Try Again.");
        }
    }

    private void validateCurrency(String ccy1, String ccy2) throws Exception {
        if(!stream(Currency.values()).map(Currency::toString).collect(toList()).containsAll(List.of(ccy1, ccy2))) {
            throw new Exception(String.format("Unable to find rate for %s/%s", ccy1, ccy2));
        }
    }
}
