package com.globant.model;

import com.globant.model.CryptoCurrencies.CryptoCurrency;

import java.math.BigDecimal;

public class Transaction {
    private String type;
    private CryptoCurrency crypto;
    private BigDecimal amount;
    private BigDecimal price;

    public Transaction(String type, CryptoCurrency crypto, BigDecimal amount, BigDecimal price){
        this.type =type;
        this.crypto = crypto;
        this.amount = amount;
        this.price = price;
    }
//Used in Wallet controller to save every transaction made
    public void check(){
        System.out.println("\n\t # Transaction # "
                + "\n Type: " + type +
                "\n Crypto: " + crypto +
                "\nAmount: " + amount +
                "\nTotal price: " +price);
    }

}
