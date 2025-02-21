package com.banking.ui;

public class UserLoginUI {


    public void displayLogInMenu() {
        System.out.println("\nUSER LOGIN: ");
        System.out.println("Enter a number: " +
                "\n\t1. Log In" +
                "\n\t2. Register"+
                "\n\t3. Exit");

    }


    public void promptUserName() {
        System.out.println("Enter your user name: ");
    }

    public void promptPIN() {
        System.out.println("Enter your 6-digit PIN: ");
    }



    //===========================================//
    //EXCEPTIONS / ERROR MESSAGES

    //EXCEPTIONS:

    public void displayUserNameInputMismatchException() {
        System.out.println("Please enter an alpha-numeric username! ");
    }

    public void displayPINInputMismatchException() {
        System.out.println("Please enter a numeric PIN only! ");
    }

    public void displayChoiceInputMismatchException() {
        System.out.println("Please enter a number!");
    }

    public void dislayUserNameInputNullPointer() {
        System.out.println("User name does not exist! Please try again");
    }

    //ERROR MESSAGES:

    public void displayErrorUserName() {
        System.out.println("Please enter your username again");
    }

    public void displayErrorPIN() {
        System.out.println("Please enter your PIN again");
    }





}
