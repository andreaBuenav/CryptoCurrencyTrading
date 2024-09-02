package com.globant.services;
import com.globant.model.Transaction;
import com.globant.model.User;
import com.globant.model.Wallet;

import java.math.BigDecimal;


public class WalletService {


    //adds Deposits into the account
    public void deposit(User user, BigDecimal amount){
       Wallet wallet = user.getWallet();
       wallet.addFunds(amount);
        Transaction transaction = new Transaction("Money Deposit:", null, null, amount, null, null);
        user.addTransaction(transaction);

    }

    }








