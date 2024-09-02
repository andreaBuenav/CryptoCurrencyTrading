package com.globant.model;

import com.globant.model.CryptoCurrencies.Bitcoin;
import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.CryptoCurrencies.Ethereum;
import com.globant.model.CryptoCurrencies.UltimaEcosystem;
import com.globant.model.exceptions.InsufficientCryptoException;
import com.globant.model.exceptions.InsufficientFundsException;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private BigDecimal balance;
    private  Map<CryptoCurrency, BigDecimal> cryptoWallet = new HashMap<>();
    //Initialize Wallet

    public Wallet(BigDecimal balance){
        cryptoWallet.put(new Bitcoin(BigDecimal.ZERO), new BigDecimal(50));
        cryptoWallet.put(new Ethereum(BigDecimal.ZERO),new BigDecimal(50));
        cryptoWallet.put(new UltimaEcosystem(BigDecimal.ZERO),new BigDecimal(50));
        this.balance = balance;
    }


//Show balance
    public BigDecimal getBalance(){return balance;}

    //Saves cryptos in the wallet
    public  Map<CryptoCurrency, BigDecimal> getCryptoWallet() {
        return cryptoWallet;
    }

    // Deposit
    public void addFunds(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void removeFunds(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        balance = balance.subtract(amount);
    }

    public void addCrypto(CryptoCurrency crypto, BigDecimal amount) {
        cryptoWallet.put(crypto, cryptoWallet.getOrDefault(crypto, BigDecimal.ZERO).add(amount));
    }

    public void removeCrypto(CryptoCurrency crypto, BigDecimal amount) {
        BigDecimal currentAmount = cryptoWallet.get(crypto);
        if (currentAmount == null || currentAmount.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient crypto balance.");
        }
        cryptoWallet.put(crypto, currentAmount.subtract(amount));
    }



}
