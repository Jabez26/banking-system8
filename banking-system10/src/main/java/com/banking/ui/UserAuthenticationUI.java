package com.banking.ui;

public class UserAuthenticationUI {

    //===========================================//
    //LOGIN

    public void userNameRegistered() {
        System.out.println("User name is registered!");
    }

    public void userNameNotRegistered() {
        System.out.println("User name is not registered! Please register first! ");
    }

    public void correctPin() {
        System.out.println("PIN is correct!");
    }

    public void incorrectPin() {
        System.out.println("PIN is incorrect! Please try again");
    }

    //===========================================//
    //REGISTRATION

    public void desiredUserNameNotAvailable() {
        System.out.println("The username you entered is already taken. Try another username or login");
    }

    public void desiredUserNameAvailable() {
        System.out.println("Username is available! ");
    }

    public void desiredPINAcceptable() {
        System.out.println("PIN is acceptable");
    }

    public void desiredPINUnacceptable() {
        System.out.println("PIN must be 6-digits long! Please try again");
    }


}
