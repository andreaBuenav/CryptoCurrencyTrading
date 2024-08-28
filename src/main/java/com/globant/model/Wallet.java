package com.globant.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WalletModel {
    private BigDecimal balance;
    private static Map<CryptoCurrency, BigDecimal> wallet = new HashMap<>();
    public Wallet(BigDecimal balance){
        this.balance = balance;

    }
}
