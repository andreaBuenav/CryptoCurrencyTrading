package com.globant.view;
import com.globant.controller.WalletController;
import com.globant.controller.UserAccountController;
import com.globant.model.CryptoCurrencies.CryptoCurrency;
import com.globant.model.exceptions.InsufficientFundsException;
import com.globant.model.exceptions.InvalidInputException;
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
    private CryptoCurrency crypto;



    //Messages
    public void showError(String errorMessage) {System.out.println(ANSI_RED + "Invalid input"  + ANSI_RESET);
    }
    public void showSignUpSuccess(){ System.out.println(ANSI_GREEN + "Signed up successfully" + ANSI_RESET);
    }
    public void showLogin(){System.out.println( ANSI_GREEN + "Login successful!" + ANSI_RESET);
    }
    public void showSuccess(){System.out.println( ANSI_GREEN + "Process successful!" + ANSI_RESET);
    }
    public void showInsufficientFundsError(String errorMessage){System.out.println( ANSI_RED + "Insufficient" + ANSI_RESET);
    }

    public UserView() {
        initMenu();
        initLoginMenu();
    }


    public void setUserAccountController(UserAccountController userAccountController) {
        this.userAccountController = userAccountController;
    }
    public void setWalletController(WalletController walletController) {
        this.walletController = walletController;
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
            System.out.println("\n\tWelcome! Please chose one option from the menu");
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

    //Calling methods from WalletController
    public void deposit(){
        walletController.execute();
    }



    public void sellCrypto(){

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
        loginMenu.put(2, this::sellCrypto);
        loginMenu.put(4, this ::showBalance);
        loginMenu.put(5, this ::showTransactions);

}
//login menu input
    public void loginMenu(){
        while(true){
            System.out.println("\n\tWelcome! Please chose one option from the menu");
            System.out.println("1. Deposit");
            System.out.println("2. Buy crypto ");
            System.out.println("3. Sell crypto ");
            System.out.println("4. Show wallet balance");
            System.out.println("5. Transactions history");
            System.out.println("6. Log out");

            String input = s.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                showError("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 6) {
                break; //Getting out of the main menu
            } else if (loginMenu.containsKey(choice)) {
                loginMenu.get(choice).run();
            } else {
                showError("Invalid option selected");
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

    public BigDecimal getCryptoAmountInput(){
        System.out.println("Enter the amount you want to purchase: ");
        try{
            BigDecimal amount = s.nextBigDecimal();
            s.nextLine();
           return amount;
        }catch(InsufficientFundsException e){
            showInsufficientFundsError(e.getMessage());
            System.out.println(ANSI_GREEN + "**Going back to the menu**" + ANSI_RESET);
            s.nextLine();
            return getCryptoAmountInput();
        }
    }

    public String getTypeOfCryptoInput(){
        System.out.println("Select the crypto you want to purchase: ");
        System.out.println(" Bitcoin -> BTC" +
                "\n Ethereum -> ETH" +
                "\n UltimaEcosystem -> ULTIMA " );

            String symbol = s.next().trim().toUpperCase();
            try{
            if (symbol.equals("BTC") || symbol.equals("ETH") || symbol.equals("ULTIMA")){
                s.nextLine();
                return symbol;
            } else {
                System.out.println(ANSI_RED + "Crypto not available!" + ANSI_RESET);
                getTypeOfCryptoInput();
        }
    }catch (InvalidInputException e){
                showError(e.getMessage());
                s.nextLine();
                return getTypeOfCryptoInput();
            }
            return symbol;
}

//First we need the marketPrice form each crypto
    public void setCryptoCurrency(CryptoCurrency crypto) {
        this.crypto = crypto;
    }

    //A crypto cannot be sold unless its price equals or is above 95% of the market price
    public BigDecimal getCryptoPriceInput() {
        System.out.println("Enter the price you want to sell the crypto for: ");
        try {
            BigDecimal inputPrice = s.nextBigDecimal();
            s.nextLine();
            BigDecimal marketPrice = crypto.getMarketPrice();
            BigDecimal minPrice = marketPrice.multiply(new BigDecimal("0.95"));
            if (inputPrice.compareTo(minPrice) < 0) {
                System.out.println("Price must be at least 95% of the market price.");
                return getCryptoPriceInput();
            }
            return inputPrice;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid price.");
            s.nextLine();
            return getCryptoPriceInput();
        }
    }


    }







