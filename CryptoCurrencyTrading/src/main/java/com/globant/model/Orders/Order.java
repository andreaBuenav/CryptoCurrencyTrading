package com.globant.model.Orders;

import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.Transaction;
import com.globant.model.User;
import com.globant.model.Wallet;

import java.math.BigDecimal;

public abstract class Order {
    private User user;
    private CryptoCurrency crypto;

    public Order(User user, CryptoCurrency crypto) {
        this.user = user;
        this.crypto = crypto;
    }

    public User getUser() {
        return user;
    }

    public CryptoCurrency getCrypto() {
        return crypto;
    }


    @Override
    public String toString() {
        return "Order{" +
                "\nusername='" + user.getUsername() + '\'' +
                "\ncrypto=" + crypto.getSymbol() +
                '}';
    }


}
