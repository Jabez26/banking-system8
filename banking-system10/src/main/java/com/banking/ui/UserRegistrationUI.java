package com.banking.ui;

public class UserRegistrationUI {

    public void promptUserName() {
        System.out.println("REGISTRATION: ");
        System.out.println("Enter your desired user name: ");
    }

    public void desiredUserNameAlreadyTaken() {
        System.out.println("Username already taken! Please try another one.");
    }

    public void promptPIN() {
        System.out.println("Enter your desired 6-digit PIN: ");
    }

    public void desiredUserNameInputMismatch() {
        System.out.println("Please enter a valid username!");
    }

    public void desiredPINInputMismatch() {
        System.out.println("Please enter a valid 6-digit PIN");
    }

    public void displayRegistrationSuccessful() {
        System.out.println("REGISTRATION SUCCESS! You may now login to your account");
    }

}
