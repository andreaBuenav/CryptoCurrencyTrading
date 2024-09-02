package com.globant.model.CryptoCurrencies;

import java.math.BigDecimal;

public class Ethereum extends CryptoCurrency {
    public Ethereum(BigDecimal marketPrice){
        super("ETH", marketPrice);

    }
}
