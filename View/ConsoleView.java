package com.globant.View;

public class ConsoleView {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";


    //Messages
    public void showError(String errorMessage) {System.out.println(ANSI_RED + "Invalid input"  + ANSI_RESET);
    }
    public void showSignUpSuccess(){ System.out.println(ANSI_GREEN + "Sign up successfully" + ANSI_RESET);
    }
    public void showLogin(){System.out.println( ANSI_GREEN + "Login successful!" + ANSI_RESET);
    }
}
