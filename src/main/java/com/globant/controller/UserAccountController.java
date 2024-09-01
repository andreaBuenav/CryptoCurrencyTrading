package com.globant.controller;

import com.globant.model.User;
import com.globant.model.Wallet;
import com.globant.services.UserService;
import com.globant.services.WalletService;
import com.globant.view.UserView;

import java.math.BigDecimal;

public class UserAccountController {
private static final String ANSI_RED = "\u001B[31m";
private static final String ANSI_GREEN = "\u001B[32m";
private static final String ANSI_RESET = "\u001B[0m";
private UserView userView;
private User currentUser;
private UserService userService;
private WalletController walletController;

    public UserAccountController(UserView userView, UserService userService ) {
      this.userView = userView;
      this.userService = userService;

    }

    public void setWalletController(WalletController walletController) {
        this.walletController = walletController;
    }


    public void signUp(){
        String name = userView.getNameInput();
        String mail = userView.getMailInput();
        if(userService.isRegistered(mail)){
            System.out.println(ANSI_RED + "Mail already in use!" + ANSI_RESET);
            signUp();
        }
        System.out.println(ANSI_GREEN+ "\t Passwords must have at least 1 number and 1 special character"+ ANSI_RESET);
        String password = userView.getPasswordInput();
//Adds  the new user to the map of users
        User newUser = new User(name, mail, password);
        userService.addUser(newUser);
        userView.showSignUpSuccess();
        System.out.println( "Username: " + newUser.getUsername() +
                "\nName: " + newUser.getName() + '\''
                );
        System.out.println(ANSI_RED+ "**Going back to the main menu..**\n"+ ANSI_RESET);


         initializeMenu(userView);
    }

    //Login
    public void login() {
        String mail = userView.getMailInput();
        String password = userView.getPasswordInput();
        if (userService.userValidator(mail, password)) {
            User user = userService.findUser(mail);
            System.out.println("Welcome back!: " + user.getUsername());
            walletController.setCurrentUser(user);
            userView.setWalletController(walletController);
            userView.showLogin();
            userView.loginMenu();
        } else {
            System.out.println(ANSI_RED + "**Invalid mail or password**" + ANSI_RESET);
            userView.showInitialMenu();
        }
    }



    public void initializeMenu(UserView userView){
        userView.showInitialMenu();
    }

}
