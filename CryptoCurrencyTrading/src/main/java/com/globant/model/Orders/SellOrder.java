package com.globant.model.Orders;
import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.Transaction;
import com.globant.model.User;
import com.globant.model.Wallet;
import com.globant.model.exceptions.InsufficientCryptoException;
import com.globant.model.exceptions.InsufficientFundsException;

import java.math.BigDecimal;

public class SellOrder extends Order {
    private BigDecimal amount;
    private BigDecimal price;

    public SellOrder(User user, CryptoCurrency crypto, BigDecimal amount, BigDecimal price) {
        super(user, crypto);
        this.amount = amount;
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
