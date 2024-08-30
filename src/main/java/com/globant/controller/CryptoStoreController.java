package com.globant.controller;

import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.CryptoStore;
import com.globant.view.UserView;

import java.math.BigDecimal;
import java.util.Map;

public class CryptoStoreController {
    private CryptoStore cryptoStore;
    private UserView userView;


    public CryptoStoreController(CryptoStore cryptoStore, UserView userView){
        this.cryptoStore = cryptoStore;
        this.userView = userView;
    }

    public void showAvailableCryptos(){
        System.out.println("Cryptocurrencies Available:");
        for (Map.Entry<CryptoCurrency, BigDecimal> entry : CryptoStore.getCryptoStore().entrySet()) {
            BigDecimal quantity = entry.getValue();
            System.out.println(entry.getKey().getSymbol() + ": $" + entry.getKey().getMarketPrice() + " available: " +  quantity);
        }
    }

}
