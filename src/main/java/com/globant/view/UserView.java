package com.globant.view;
import com.globant.controller.WalletController;
import com.globant.controller.UserAccountController;
import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.exceptions.InsufficientFundsException;
import com.globant.model.exceptions.InvalidInputException;
import com.globant.services.OrderService;
import com.globant.services.UserService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class UserView {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private final static Scanner s = new Scanner(System.in);
    private UserAccountController userAccountController;
    private WalletController walletController;
    private OrderService orderService;




    //Messages
    public void showError(String errorMessage) {System.out.println(ANSI_RED + "Invalid input"  + ANSI_RESET);
    }
    public void showSignUpSuccess(){ System.out.println(ANSI_GREEN + "Signed up successfully" + ANSI_RESET);
    }
    public void showLogin(){System.out.println( ANSI_GREEN + "Login successful!" + ANSI_RESET);
    }
    public void showSuccess(){System.out.println( ANSI_GREEN + "Process successful!" + ANSI_RESET);
    }


    //Initializer
    public UserView() {
        initMenu();
        initTradeMenu();
        initLoginMenu();
    }


    public void setUserAccountController(UserAccountController userAccountController) {
        this.userAccountController = userAccountController;
    }
    public void setWalletController(WalletController walletController) {
        this.walletController = walletController;
    }
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }



    //Menu principal
private static final  Map<Integer, Runnable> mainMenuOptions = new HashMap<>();
    //Calling Sign up and log in from User Controller
    public void signUp(){
       userAccountController.signUp();
    }

    public void logIn(){
        userAccountController.login();

    }

    private void initMenu(){
        mainMenuOptions.put( 1, this::signUp);
        mainMenuOptions.put(2, this::logIn);
    }



    //Show initial menu
    public void showInitialMenu(){
        while (true) {
            System.out.println("\n\t--Welcome! Please chose one option from the menu--");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String input = s.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                showError("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 3) {
                break; //Getting out of the main menu
            } else if (mainMenuOptions.containsKey(choice)) {
                mainMenuOptions.get(choice).run();
            } else {
                showError("Invalid option selected");
            }
        }
    }

    //Login menu
    private static final Map<Integer, Runnable> loginMenu = new HashMap<>();
    public void trade(){
       tradeMenu();
    }
    //Calling methods from WalletController
    public void deposit(){
        walletController.deposit();
    }

    public void showBalance(){
        System.out.println("Account Balance: \n" );
        walletController.balance();
    }
    public void showTransactions(){
        System.out.println("Account Transactions: \n" );
        walletController.showTransactionHistory();
    }


//Login menu map values
private void initLoginMenu(){
        loginMenu.put(1, this::deposit);
        loginMenu.put(2, this::trade);
        loginMenu.put(3, this ::showBalance);
        loginMenu.put(4, this ::showTransactions);

}
//login menu input
    public void loginMenu(){
        while(true){
            System.out.println("\n\t--Login menu--");
            System.out.println("\n\tPSA: Check your transactions/balance your sell offers could've been done");
            System.out.println("\n\tWelcome! Please chose one option from the menu");
            System.out.println("1. Deposit");
            System.out.println("2. Trading menu ");
            System.out.println("3. Show wallet balance");
            System.out.println("4. Transactions history");
            System.out.println("5. Logout");

            String input = s.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                showError("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 5) {
                break; //Getting out of the main menu
            } else if (loginMenu.containsKey(choice)) {
                loginMenu.get(choice).run();
            } else {
                showError("Invalid option selected");
            }
        }

    }



    //Methods
    public void buy(){
        try {
            walletController.purchaseOrder();
        } catch (InsufficientFundsException e) {
            System.out.println(ANSI_RED + "Insufficient funds to complete the purchase." + ANSI_RESET);
            tradeMenu();
        }

    }

    public void sell(){
    walletController.sellOrder();
    }

    public void ShowSellOrders(){
       orderService.showSellOrders();
    }




    //Trading menu
    private static final Map<Integer, Runnable> tradeMenu = new HashMap<>();

    private void initTradeMenu(){
        tradeMenu.put(1, this::buy);
        tradeMenu.put(2, this::sell);
        tradeMenu.put(3, this::ShowSellOrders);
        tradeMenu.put(4, this::loginMenu);

    }


    public void tradeMenu() {
        while (true) {
            System.out.println("\n\t-- Trading menu --");
            System.out.println("\n\t Please choose one option from the menu");
            System.out.println("1. Buy cryptos");
            System.out.println("2. Sell cryptos");
            System.out.println("3. Show sell orders");
            System.out.println("4. Back to login menu");


            String input = s.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                showError("Invalid input. Please enter a number.");
                continue;
            }
            if (choice == 4) {
                break; // Exit the trade menu
            } else if (tradeMenu.containsKey(choice)) {
                tradeMenu.get(choice).run();
            } else {
                showError("Invalid option selected");
                s.nextLine();
            }
        }
    }


//User inputs
    public String getNameInput(){
        try{
            System.out.println("Enter your full name");
            String name = s.nextLine();
            UserService userService = new UserService();
            userService.nameValidator(name);
            return name;
        }catch (InvalidInputException e){
            showError(e.getMessage());
            return getNameInput();

        }

    }
    public String getMailInput(){
        System.out.println("Enter your mail address");
        try{
            String mail = s.nextLine();
            UserService userService = new UserService();
            userService.mailValidator(mail);
            return mail;
        }catch (InvalidInputException e){
            showError(e.getMessage());
            return getMailInput();
        }

    }
    public String getPasswordInput(){
        System.out.println("Enter your password:");
        try{
            String password = s.nextLine();
            UserService userService = new UserService();
            userService.passwordValidator(password);
            return password;
        }catch (InvalidInputException e){
            showError(e.getMessage());
            return getPasswordInput();
        }

    }

    //Wallet inputs
    public BigDecimal getDepositInput(){
        System.out.println("Enter the amount you want to deposit into your wallet: ");
        try{
     BigDecimal amount = s.nextBigDecimal();
     s.nextLine();
     return amount;
        }catch(InputMismatchException e){
            showError(e.getMessage());
            s.nextLine();
            return getDepositInput();
        }
    }

    public BigDecimal getCryptoAmountInput() {
        System.out.println("Enter an amount (It can be integer or decimal): ");
        while (true) {
            try {
                String input = s.nextLine();
                BigDecimal amount = new BigDecimal(input);
                if (amount.signum() <= 0) {
                    throw new IllegalArgumentException(ANSI_RED +"Amount must be greater than 0." +ANSI_RESET);
                } else if (amount.compareTo(BigDecimal.ONE) < 0) {
                    throw new IllegalArgumentException(ANSI_RED +"You cannot sell less than 1 crypto." + ANSI_RESET);
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid amount format. Please enter a valid number." + ANSI_RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public String getTypeOfCryptoInput() {
        System.out.println("Select one type of crypto: ");
        System.out.println(" Bitcoin -> BTC" +
                "\n Ethereum -> ETH" +
                "\n UltimaEcosystem -> ULTIMA");

        String symbol = s.next().trim().toUpperCase();
        s.nextLine();

        if (symbol.equals("BTC") || symbol.equals("ETH") || symbol.equals("ULTIMA")) {
            return symbol;
        } else {
            System.out.println(ANSI_RED + "Crypto not available or incorrect input!" + ANSI_RESET);
            return getTypeOfCryptoInput();
        }
    }


    //A crypto cannot be sold unless its price equals or is greater than 95% of the market price
    public BigDecimal getCryptoPriceInput(CryptoCurrency crypto) {
        BigDecimal marketPrice = crypto.getMarketPrice();
        BigDecimal minPrice = marketPrice.multiply(new BigDecimal("0.95"));
        System.out.println("Market Price: " + marketPrice);
        System.out.println("Minimum Price (95% of Market Price): " + minPrice);
        System.out.println("Enter an offer for each crypto: ");
        try {
            BigDecimal inputPrice = s.nextBigDecimal();
            System.out.println("Input Price: " + inputPrice);
            OrderService orderService = new OrderService();
            orderService.marketPriceValidator(inputPrice, crypto);
            s.nextLine();
            return inputPrice;
        } catch (InvalidInputException e) {
            System.out.println("Invalid input. Please enter a valid price.");
            return getCryptoPriceInput(crypto);
        }
    }


    }







