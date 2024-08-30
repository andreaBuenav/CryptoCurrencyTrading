package com.globant.model;

import com.globant.model.CryptoCurrencies.Bitcoin;
import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.CryptoCurrencies.Ethereum;
import com.globant.model.CryptoCurrencies.UltimaEcosystem;

import java.math.BigDecimal;
import java.util.*;

public class CryptoStore {
    private static final Map<CryptoCurrency, BigDecimal> cryptoStore = new HashMap<>();


    //Every user has this store, so that they can purchase their first cryptos to start trading
    public CryptoStore(){
        cryptoStore.put(new Bitcoin(new BigDecimal("59474.14")), new BigDecimal("100"));
        cryptoStore.put(new Ethereum(new BigDecimal("2524.92")), new BigDecimal("100"));
        cryptoStore.put(new UltimaEcosystem(new BigDecimal("6801.43")), new BigDecimal("100"));
    }

    public void reduceCryptoInventory(CryptoCurrency crypto, BigDecimal amount) {
        if (cryptoStore.containsKey(crypto)) {
            BigDecimal currentAmount = cryptoStore.get(crypto);
            if (currentAmount.compareTo(amount) >= 0) {
                cryptoStore.put(crypto, currentAmount.subtract(amount));
            } else {
                throw new IllegalArgumentException("Not enough cryptocurrency in store.");
            }
        } else {
            throw new IllegalArgumentException("Cryptocurrency not found in store.");
        }
    }
    public BigDecimal getCrypto(CryptoCurrency crypto) {
        return cryptoStore.getOrDefault(crypto, BigDecimal.ZERO);
    }

    public static Map<CryptoCurrency, BigDecimal> getCryptoStore() {
        return cryptoStore;
    }


}
