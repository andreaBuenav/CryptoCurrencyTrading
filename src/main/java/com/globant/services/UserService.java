package com.globant.services;

import com.globant.model.User;
import com.globant.model.exceptions.InvalidInputException;

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


    public String mailValidator(String mail)throws InvalidInputException {
        if (!mail.contains("@") || !mail.endsWith(".com")) {
            throw new InvalidInputException();
        }
        return mail;
    }

        //Validating name

        public String nameValidator (String name)throws InvalidInputException {
            if (!name.matches("^[a-zA-Z ]+$")) {
                throw new InvalidInputException();
            }
            return name;
        }

        // Validates if passwords that have been entered are correctly structured.
        public String passwordValidator (String password)throws InvalidInputException {
            String sc = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{}|;':\",.<>?/]).+$";
            if (!password.matches(sc)) {
                throw new InvalidInputException();
            }
            return password;
        }


    }



