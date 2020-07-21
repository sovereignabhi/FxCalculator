package com.anz.fx.calculator.service;

import com.anz.fx.calculator.constant.Currency;

/**
 * Created by abhis on 19/07/2020.
 */
public class ConversionServiceImpl implements ConversionService {

    public Double calculateRate(Currency baseCcy, Currency termCcy) {
        if(baseCcy.equals(termCcy)) {
            return 1d;
        }
        Double rateFromTable = getConversionRate(baseCcy, termCcy);
        if(rateFromTable == null) {
            Currency crossCurrency = getCrossOverCurrency(baseCcy, termCcy);
            return calculateRate(baseCcy, crossCurrency) / calculateRate(termCcy, crossCurrency);
        } else {
            return rateFromTable;
        }
    }

    private Double getConversionRate(Currency baseCcy, Currency termCcy) {
        Double rateFromTable = CONVERSION_RATES.get(String.join("/", baseCcy.name(), termCcy.name()));
        if(rateFromTable == null) {
            rateFromTable = invertRate(CONVERSION_RATES.get(String.join("/", termCcy.name(), baseCcy.name())));
        }
        return rateFromTable;
    }

    private Currency getCrossOverCurrency(Currency baseCcy, Currency termCcy) {
        Currency ccy = CROSSOVER_CURRENCIES.get(baseCcy);
        if(ccy == null) {
            ccy = CROSSOVER_CURRENCIES.get(termCcy);
        }
        return ccy;
    }

    private Double invertRate(Double rate) {
        return rate == null ? null : 1/rate;
    }
}
