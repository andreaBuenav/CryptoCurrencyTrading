package com.globant.services;

import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.CryptoStore;
import com.globant.model.Transaction;
import com.globant.model.User;
import com.globant.model.Wallet;
import com.globant.model.exceptions.InsufficientFundsException;
import com.globant.model.exceptions.InvalidInputException;
import com.globant.view.UserView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WalletService {

    private static final Map<String, BigDecimal> wallets = new HashMap<>();
    public List<Transaction> transactionHistory = new ArrayList<>();



    //adds Deposits into the account
    public void deposit(User user, BigDecimal amount){
       Wallet wallet = user.getWallet();
       wallet.addFounds(amount);
        Transaction transaction = new Transaction("Money Deposit:", null, amount, null);
        user.addTransaction(transaction);

    }

    //Adds cryptos to the wallet From the store
    public void purchaseCryptoFromStore(User user, CryptoStore cryptoStore, CryptoCurrency crypto, BigDecimal amount) {
        if (crypto == null) {
            throw new InvalidInputException();
        }

        Wallet wallet = user.getWallet(); // Get the user's wallet
        BigDecimal price = crypto.getMarketPrice();
        BigDecimal totalCost = amount.multiply(price);

        if (wallet.getBalance().compareTo(totalCost) >= 0) {
            BigDecimal availableCrypto = cryptoStore.getCrypto(crypto);
            if (availableCrypto.compareTo(amount) >= 0) {
                wallet.removeFunds(totalCost);
                wallet.addCrypto(crypto, amount);
                cryptoStore.reduceCryptoInventory(crypto, amount);
                Transaction transaction = new Transaction("Crypto purchase from store:", crypto, amount, price);
                user.addTransaction(transaction);
            } else {
                System.out.println("Insufficient crypto in the store.");
                throw new InsufficientFundsException();
            }
        } else {
            throw new InsufficientFundsException();

        }
    }

    //Adds cryptos from trades
    public void purchaseFromTrade(Wallet wallet){

    }




    //Removes cryptos from wallet
    public void sellCrypto(Wallet wallet, CryptoCurrency crypto, BigDecimal amount, BigDecimal price) {
        wallets.remove(crypto, amount);
    }









    }








