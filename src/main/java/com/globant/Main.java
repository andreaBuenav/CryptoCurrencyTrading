package com.globant;
import com.globant.controller.WalletController;
import com.globant.controller.UserAccountController;
import com.globant.model.CryptoStore;
import com.globant.services.WalletService;
import com.globant.view.UserView;
import com.globant.services.UserService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       //UserView
        UserView userView = new UserView();

        //Services:
        UserService userService = new UserService();
        WalletService walletService = new WalletService();
        CryptoStore cryptoStore = new CryptoStore();

        //Controllers
        UserAccountController userAccountController = new UserAccountController(userView, userService);
        WalletController walletController = new WalletController(userView, walletService);

        //Calling setters
        userView.setUserAccountController(userAccountController);
        userView.setWalletController(walletController);
        userView.setCryptoStore(cryptoStore);
       //Initializing program:
        userView.showInitialMenu();




        
    }
}