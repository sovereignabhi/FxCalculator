package com.anz.fx.calculator.service;

import com.anz.fx.calculator.constant.Currency;

import java.util.Map;

import static com.anz.fx.calculator.constant.Currency.*;
import static java.util.Map.entry;

/**
 * Created by abhis on 19/07/2020.
 */
public interface ConversionService {

    Double calculateRate(Currency baseCcy, Currency termCcy);

    //stored in a db table or file, using map as there are only 10 entries
    Map<String, Double> CONVERSION_RATES = Map.ofEntries(
            entry("AUD/USD", 0.8371),
            entry("CAD/USD", 0.8711),
            entry("USD/CNY", 6.1715),
            entry("EUR/USD", 1.2315),
            entry("GBP/USD", 1.5683),
            entry("NZD/USD", 0.7750),
            entry("USD/JPY", 119.95),
            entry("EUR/CZK", 27.6028),
            entry("EUR/DKK", 7.4405),
            entry("EUR/NOK", 8.6651)
    );

    //stored in a db table or file, using map as there are only 10 entries
    Map<Currency, Currency> CROSSOVER_CURRENCIES = Map.ofEntries(
        entry(AUD,USD),
        entry(CAD,USD),
        entry(CNY,USD),
        entry(EUR,USD),
        entry(GBP,USD),
        entry(JPY,USD),
        entry(NZD,USD),
        entry(CZK,EUR),
        entry(DKK,EUR),
        entry(NOK,EUR)
        );
}
