package com.anz.fx.calculator.main;

import com.anz.fx.calculator.constant.Currency;
import com.anz.fx.calculator.service.ConversionService;
import com.anz.fx.calculator.service.ConversionServiceImpl;
import com.anz.fx.calculator.service.UserInputReader;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by abhis on 19/07/2020.
 */
public class FXCalculatorApplication {
    ConversionService conversionService = new ConversionServiceImpl();

    public static void main(String[] a) {
        FXCalculatorApplication fxCalculatorApplication = new FXCalculatorApplication();
        UserInputReader userInputReader = new UserInputReader();
        try {
            List<String> input = userInputReader.readUserInput();
            fxCalculatorApplication.convertAndDisplay(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            main(new String[]{});
        }
    }

    private void convertAndDisplay(List<String> input) {
        Currency baseCcy = Currency.valueOf(input.get(0));
        Currency termCcy = Currency.valueOf(input.get(1));
        Double inputAmount = Double.valueOf(input.get(2));

        Double conversionRate = conversionService.calculateRate(baseCcy, termCcy);
        String convertedAmount = formatAmount(termCcy, inputAmount, conversionRate);
        System.out.println(String.format("%s %s = %s %s", baseCcy, inputAmount, termCcy, convertedAmount));
    }

    protected String formatAmount(Currency termCcy, Double inputAmount, Double conversionRate) {
        return new DecimalFormat(termCcy.getPrecision()).format(inputAmount * conversionRate);
    }

}
