package com.globant.model.CryptoCurrencies;

import java.math.BigDecimal;

public class Bitcoin extends CryptoCurrency{
    public Bitcoin(BigDecimal marketPrice){
        super("BTC", CryptoCurrency.getPrice("BTC"));
    }

}
