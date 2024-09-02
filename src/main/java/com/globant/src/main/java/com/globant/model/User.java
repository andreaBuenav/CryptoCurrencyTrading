package com.globant.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private String name;
    private String mail;
    private String password;
    private String username;
    private Wallet wallet;
    private List<Transaction> transactionHistory = new ArrayList<>();


    public User(String name, String mail, String password){
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.wallet = new Wallet(BigDecimal.ZERO);
        this.username = UsernameGenerator(mail);

    }


    public String getName() {return name;}
    public String getMail() {return mail;}
    public String getPassword() {return password;}
    public String getUsername(){return username;}
    public Wallet getWallet(){return wallet;}
    public void setWallet(Wallet wallet) { this.wallet = wallet; }




    //Creates a user using the mail input before the '@' and adds random numbers at the end
    public  String UsernameGenerator( String email){
        String username;
        Random random = new Random();
        int randomUser = random.nextInt(5000);
        username = email.split("@")[0] + randomUser;
        return username;

    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }




}
