package com.globant.model;

import com.globant.model.CryptoCurrencies.CryptoCurrency;

import java.math.BigDecimal;

public class Transaction {
    private String type;
    private CryptoCurrency crypto;
    private String buyer;
    private String seller;
    private BigDecimal amount;
    private BigDecimal price;

    public Transaction(String type, String seller, CryptoCurrency crypto, BigDecimal amount, BigDecimal price, String buyer){
        this.type =type;
        this.crypto = crypto;
        this.amount = amount;
        this.price = price;
        this.buyer = buyer;
        this.seller= seller;
    }
//Used in Wallet controller to save every transaction made
public void check() {
    System.out.println("\n\t # Transaction # "
            + "\n Type: " + type +
            "\n Buyer: " + (buyer != null ? buyer : "N/A") +
            "\n Seller: " + (seller != null ? seller : "N/A") +
            "\n Crypto: " + (crypto != null ? crypto.getSymbol() : "N/A") +
            "\n Amount: " + amount +
            "\n Total price: " + (price != null ? price : "N/A"));
}

}
