package com.globant;
import com.globant.controller.WalletController;
import com.globant.controller.UserAccountController;
import com.globant.model.Orders.Order;
import com.globant.services.OrderService;
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
        OrderService orderService = new OrderService();


        //Controllers
        UserAccountController userAccountController = new UserAccountController(userView, userService);
        WalletController walletController = new WalletController(userView, walletService, orderService);

        //Calling setters
        userView.setUserAccountController(userAccountController);
        userView.setWalletController(walletController);
        userView.setOrderService(orderService);
        userAccountController.setWalletController(walletController);
       //Initializing program:
        userView.showInitialMenu();




        
    }
}