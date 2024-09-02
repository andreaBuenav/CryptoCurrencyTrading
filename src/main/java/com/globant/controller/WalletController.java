package com.globant.controller;

import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.Orders.PurchaseOrder;
import com.globant.model.Orders.SellOrder;
import com.globant.model.Transaction;
import com.globant.model.User;
import com.globant.model.Wallet;
import com.globant.services.OrderService;
import com.globant.services.WalletService;
import com.globant.view.UserView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class WalletController {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private UserView userView;
    private OrderService orderService;
    private User user;
    private WalletService walletService;


    public WalletController(UserView userView, WalletService walletService, OrderService orderService){
        this.userView = userView;
        this.walletService = walletService;
        this.orderService = orderService;

}
    protected void setCurrentUser(User user) {
        this.user = user;
    }

    public void deposit() {
        if (user == null) {
            System.out.println("No user is currently set.");
            return;
        }
        BigDecimal amount = userView.getDepositInput();
        Wallet wallet = user.getWallet();
        walletService.deposit(user, amount);
        System.out.println("Deposited " + amount + ". New balance: " + wallet.getBalance());
        userView.showSuccess();
        System.out.println(ANSI_GREEN + "--Going back to the main menu--\n" + ANSI_RESET);
    }


    public void balance() {
        if (user == null) {
            System.out.println("No user is currently set.");
            return;
        }
        Wallet wallet = user.getWallet();
        BigDecimal balance = wallet.getBalance();
        System.out.println("Current balance: $" + balance);
        System.out.println("Cryptocurrency balances:");
        for (Map.Entry<CryptoCurrency, BigDecimal> entry : wallet.getCryptoWallet().entrySet()) {
            System.out.println(entry.getKey().getSymbol() + ": " + entry.getValue());
        }
        System.out.println(ANSI_GREEN + "--Going back to the main menu--\n" + ANSI_RESET);


    }
    public void purchaseOrder() {
        if (user == null) {
            System.out.println("No user is currently set.");
            return;
        }

        String cryptoSymbol = userView.getTypeOfCryptoInput();
        CryptoCurrency crypto = CryptoCurrency.getCryptoBySymbol(cryptoSymbol);
        BigDecimal amount = userView.getCryptoAmountInput();
        BigDecimal price = userView.getCryptoPriceInput(crypto);

        PurchaseOrder purchaseOrder = new PurchaseOrder(user, crypto, amount, price);
        orderService.addBuyOrder(purchaseOrder);

        System.out.println("Buy order placed for " + amount + " " + cryptoSymbol + " at $" + price);
        userView.showSuccess();
        System.out.println(ANSI_GREEN + "--Going back to the main menu--\n" + ANSI_RESET);
    }

    public void sellOrder() {
        if (user == null) {
            System.out.println("No user is currently set.");
            return;
        }

        String cryptoSymbol = userView.getTypeOfCryptoInput();
        CryptoCurrency crypto = CryptoCurrency.getCryptoBySymbol(cryptoSymbol);
        BigDecimal amount = userView.getCryptoAmountInput();
        BigDecimal price = userView.getCryptoPriceInput(crypto);

        SellOrder sellOrder = new SellOrder(user, crypto, amount, price);
        orderService.addSellOrder(sellOrder);
        System.out.println("Sell order placed for " + amount + " " + cryptoSymbol + " at $" + price);
        userView.showSuccess();
        System.out.println(ANSI_GREEN + "--Going back to the main menu--\n" + ANSI_RESET);
    }



    public void showTransactionHistory() {
        List<Transaction> transactions = user.getTransactionHistory();
        for (Transaction transaction : transactions) {
            transaction.check();
        }
    }

}







