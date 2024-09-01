package com.globant.services;

import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.Orders.PurchaseOrder;
import com.globant.model.Orders.SellOrder;
import com.globant.model.Transaction;
import com.globant.model.User;
import com.globant.model.Wallet;
import com.globant.model.exceptions.InsufficientCryptoException;
import com.globant.model.exceptions.InsufficientFundsException;
import com.globant.model.exceptions.InvalidInputException;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OrderService {
    private Queue<PurchaseOrder> purchaseOrders = new LinkedList<>();
    private Queue<SellOrder> sellOrders = new LinkedList<>();

    public void addBuyOrder(PurchaseOrder buyOrder) {
        purchaseOrders.add(buyOrder);
        processOrders();
    }

    public void addSellOrder(SellOrder sellOrder) {
        sellOrders.add(sellOrder);
        processOrders();
    }

    private void processOrders() {
        while (!purchaseOrders.isEmpty() && !sellOrders.isEmpty()) {
            PurchaseOrder purchaseOrder = purchaseOrders.peek();
            SellOrder sellOrder = sellOrders.peek();

            if (canExecuteOrder(purchaseOrder, sellOrder)) {
                executeOrder(purchaseOrder, sellOrder);
            } else {
                break;
            }
        }
    }

    private boolean canExecuteOrder(PurchaseOrder purchaseOrder, SellOrder sellOrder) {
        return purchaseOrder.getAmount().compareTo(sellOrder.getAmount()) >= 0
                && purchaseOrder.getPrice().compareTo(sellOrder.getPrice()) >= 0;
    }

    private void executeOrder(PurchaseOrder purchaseOrder, SellOrder sellOrder) {
        BigDecimal tradeAmount = sellOrder.getAmount().min(purchaseOrder.getAmount());
        BigDecimal tradePrice = sellOrder.getPrice().divide(sellOrder.getAmount(), RoundingMode.HALF_UP); // Precio por unidad


        User buyer = purchaseOrder.getUser();
        User seller = sellOrder.getUser();
        Wallet buyerWallet = buyer.getWallet();
        Wallet sellerWallet = seller.getWallet();

        BigDecimal buyerBalance = buyerWallet.getBalance();
        BigDecimal totalCost = tradeAmount.multiply(tradePrice);

        if (buyerBalance.compareTo(totalCost) >= 0) {
            buyerWallet.removeFunds(totalCost);
            sellerWallet.addFunds(totalCost);

            buyerWallet.addCrypto(purchaseOrder.getCrypto(), tradeAmount);
            sellerWallet.removeCrypto(sellOrder.getCrypto(), tradeAmount);

            Transaction buyTransaction = new Transaction("BUY", seller.getUsername(), purchaseOrder.getCrypto(), tradeAmount, tradePrice, buyer.getUsername());
            Transaction sellTransaction = new Transaction("SELL", buyer.getUsername(), sellOrder.getCrypto(), tradeAmount, tradePrice, seller.getUsername());

            buyer.addTransaction(buyTransaction);
            seller.addTransaction(sellTransaction);

            notifyUser(buyer, "Purchase completed: " + tradeAmount + " " + purchaseOrder.getCrypto().getSymbol() + " at unitary price: $" + tradePrice);
            notifyUser(seller, "Sale completed: " + tradeAmount + " " + sellOrder.getCrypto().getSymbol() + " at at unitary price $" + tradePrice);

            if (purchaseOrder.getAmount().compareTo(tradeAmount) == 0) {
                purchaseOrders.poll();
            } else {
                purchaseOrder.setAmount(purchaseOrder.getAmount().subtract(tradeAmount));
            }

            if (sellOrder.getAmount().compareTo(tradeAmount) == 0) {
                sellOrders.poll();
            } else {
                sellOrder.setAmount(sellOrder.getAmount().subtract(tradeAmount));
            }
        } else {
            throw new InsufficientFundsException();
        }
    }

    private void notifyUser(User user, String message) {
        // Implement the notification logic (e.g., email, system message)
        System.out.println("Notification to " + user.getUsername() + ": " + message);
    }


    public void marketPriceValidator(BigDecimal price, CryptoCurrency crypto) throws InvalidInputException {
        if (crypto == null) {
            throw new IllegalArgumentException("CryptoCurrency cannot be null.");
        }
        BigDecimal marketPrice = crypto.getMarketPrice();
        BigDecimal minPrice = marketPrice.multiply(new BigDecimal("0.95"));
        if (price.compareTo(minPrice) < 0) {
            System.out.println("Price must be at least 95% of the market price.");
            throw new InvalidInputException();
        }
    }
}

