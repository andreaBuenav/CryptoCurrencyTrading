package com.globant.services;

import com.globant.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final static Map<String , User> users = new HashMap<>();

    //Sign up parameters
    //Adding users
    public void addUser(User user){
        users.put(user.getMail(), user);
    }
    //Checks if mail is already in use
    public boolean isRegistered(String mail){
        return users.containsKey(mail);
    }

    //Login parameters
    //find by mail
    public User findUser(String mail){
        isRegistered(mail);
        return users.get(mail);
    }
    //Validating mail and password
    public boolean userValidator(String mail, String password){
        User user = findUser(mail);
        return user != null && user.getPassword().equals(password);
    }

    public String getInfo(User user){
        return "User{" +
                "name='" + user.getName() + '\'' +
                ", mail='" + user.getMail() + '\'' +
                ", password='" + user.getPassword() + '\'' +
                ", username='" +  user.getUsername() +
                ", wallet=" + user.getWallet() +
                '}';
    }

}
