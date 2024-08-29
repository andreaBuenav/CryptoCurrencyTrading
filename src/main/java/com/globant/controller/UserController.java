package com.globant.controller;

import com.globant.model.User;
import com.globant.model.Wallet;
import com.globant.services.UserService;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

public class UserController {
private static final String ANSI_RED = "\u001B[31m";
private static final String ANSI_GREEN = "\u001B[32m";
private static final String ANSI_RESET = "\u001B[0m";
private ConsoleView consoleView;
private UserService userService;

    public UserController(ConsoleView consoleView, UserService userService) {
      this.consoleView= consoleView;
      this.userService = userService;
    }

    public void signUp(){
        String name = consoleView.getNameInput();
        String mail = consoleView.getMailInput();
        if(userService.isRegistered(mail)){
            System.out.println(ANSI_RED + "Mail already in use!" + ANSI_RESET);
        }
        System.out.println(ANSI_GREEN+ "\t Passwords must have at least 1 number and 1 special character"+ ANSI_RESET);
        String password = consoleView.getPasswordInput();
//Adds  the new user to the map of users
        User newUser = new User(name, mail, password);
        userService.addUser(newUser);
        consoleView.showSignUpSuccess();
        System.out.println( "Username: " + newUser.getUsername() +
                "\nName: " + newUser.getName() + '\'' +
                "\n Mail: " + newUser.getMail() + '\'' +
                "\n Password: " + newUser.getPassword() + '\''
                );
        System.out.println(ANSI_RED+ "**Going back to the main menu..**\n"+ ANSI_RESET);


         initializeMenu(consoleView);
    }

    //Login
    public void login(){
        String mail= consoleView.getMailInput();
        String password = consoleView.getPasswordInput();
        if (userService.userValidator(mail, password)){
            User user = userService.findUser(mail);
            System.out.println("Welcome back!: " + user.getUsername());
            consoleView.showLogin();
            consoleView.loginMenu();
        }else {
            System.out.println(ANSI_RED + "**Invalid mail or password**" + ANSI_RESET);
            login();
        }
    }

    public void initializeMenu(ConsoleView consoleView){
        consoleView.showInitialMenu();
    }

}
