package com.globant.model;

import java.math.BigDecimal;
import java.util.Random;

public class UserModel {
    private String name;
    private String mail;
    private String password;
    private String username;
    private Wallet wallet;


    public User(String name, String mail, String password){
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.username = UsernameGenerator(mail);
        this.wallet = new Wallet(BigDecimal.ZERO);
    }



    public String getName() {return name;}
    public String getMail() {return mail;}
    public String getPassword() {return password;}
    public String getUsername(){return username;}
    public Wallet getWallet(){return wallet;}

    //Creates a user using the mail input before the '@' and adds random numbers at the end
    public  String UsernameGenerator( String email){
        String username;
        Random random = new Random();
        int randomUser = random.nextInt(5000);
        username = email.split("@")[0] + randomUser;
        return username;

    }

}
