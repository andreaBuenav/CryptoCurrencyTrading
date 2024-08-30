package com.globant.model.CryptoCurrencies;

import java.math.BigDecimal;

public class UltimaEcosystem extends CryptoCurrency {
    public UltimaEcosystem(BigDecimal marketPrice){
        super("ULTIMA" , CryptoCurrency.getPrice("ULTIMA"));
    }
}
