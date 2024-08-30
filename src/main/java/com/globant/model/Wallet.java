package com.globant.model;

import com.globant.model.CryptoCurrencies.Bitcoin;
import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.CryptoCurrencies.Ethereum;
import com.globant.model.CryptoCurrencies.UltimaEcosystem;
import com.globant.model.exceptions.InsufficientFundsException;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private BigDecimal balance;
    private static Map<CryptoCurrency, BigDecimal> cryptoWallet = new HashMap<>();
    //Initialize Wallet

    public Wallet(BigDecimal balance){
        cryptoWallet.put(new Bitcoin(BigDecimal.ZERO), BigDecimal.ZERO);
        cryptoWallet.put(new Ethereum(BigDecimal.ZERO),BigDecimal.ZERO);
        cryptoWallet.put(new UltimaEcosystem(BigDecimal.ZERO),BigDecimal.ZERO);
        this.balance = balance;
    }


//Show balance
    public BigDecimal getBalance(){return balance;}

    public static Map<CryptoCurrency, BigDecimal> getCryptoWallet() {
        return cryptoWallet;
    }

    //Deposit
    public void addFounds(BigDecimal amount){
         balance = balance.add(amount);
    }

    //add cryptos
    public void addCrypto(CryptoCurrency crypto, BigDecimal amount){
        cryptoWallet.put(crypto, cryptoWallet.get(crypto).add(amount));
    }
    public void removeFunds(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new InsufficientFundsException();
        }

    }


    public void removeCrypto(CryptoCurrency crypto, BigDecimal amount) {
        if (cryptoWallet.get(crypto).compareTo(amount) >= 0) {
            cryptoWallet.put(crypto, cryptoWallet.get(crypto).subtract(amount));
        } else {
            throw new InsufficientFundsException();
        }
    }




}
