package com.banking.service;

import com.banking.ui.UserAuthenticationUI;
import com.banking.ui.UserLoginUI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserLogin {

    private static UserLoginUI userLoginUI;

    private static Scanner scanner;
    private static int accountNumber;   //the current user's account number
    private static String userName;     //the current user using the app
    private static int PIN;             //the current user's PIN
    private static double balance;      //the current user's balance

    //temporary storage:
    //stores data inputted during registration, cuz program does not have a refresh directory feature, that updates directory of users.txt file everytime a new users.txt file is created
    private static String userNameFrmRegistration;          //set current user using the app
    private static int accountNumberFrmRegistration;        //set current user's account number
    private static int PINFrmRegistration;                  //set current user's PIN
    private static double balanceFrmRegistration;           //set current user's balance

    public static boolean attemptRegistration = false;
    public static boolean loginSuccess = false;

    //CONSTRUCTOR
    public UserLogin(UserLoginUI userLoginUI) {
        userNameFrmRegistration = UserRegistration.getUserName();              //set current user using the app
        PINFrmRegistration = UserRegistration.getPIN();                        //set current user's PIN
        setAccountNumber(accountNumberFrmRegistration);

        UserLogin.userLoginUI = userLoginUI;
        userLoginUI.displayLogInMenu(); //display login menu
        logInMenuUserInput();           //listen for user input
    }

    //METHOD logInMenuUserInput()
    public void logInMenuUserInput() {

        scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //Start login authentication:
                    UserAuthenticationUI userAuthenticationUI = new UserAuthenticationUI();                 //setup UI
                    UserAuthentication userAuthentication = new UserAuthentication(userAuthenticationUI);   //setup authentication logic
                    login(userAuthentication);
                    System.out.println("LOGIN SUCCESS!");
                    loginSuccess = true;
                    break;
                case 2:
                    attemptRegistration = true;
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } catch (InputMismatchException e) {
            userLoginUI.displayChoiceInputMismatchException(); //error if user inputs something other than an integer.
            logInMenuUserInput(); //Recursion
        }

    }

    //METHOD login()
    public void login(UserAuthentication userAuthentication) {

        authenticateUserName(userAuthentication);

        authenticatePIN(userAuthentication);

    }

    //METHOD
    //Check if userName exists in database:
    public void authenticateUserName(UserAuthentication userAuthentication) {
        boolean isUserNameAuthenticated = false;

        while(!isUserNameAuthenticated) {
            enterUserName();    //prompt user for userName and collect user-input
            accountNumber = userAuthentication.retrieveAccountNumber(userName);     //retrieve accountNumber by userName
            isUserNameAuthenticated = userAuthentication.checkUserName(userName);   //authenticate userName

        }

    }

    //METHOD
    //Check if PIN corresponds to userName
    public void authenticatePIN(UserAuthentication userAuthentication) {
        boolean isPINCorrect = false;

        while(!isPINCorrect) {
            enterPIN();                          //prompt user for PIN and collect user-input
            isPINCorrect = userAuthentication.checkPIN(userName, PIN);       //authenticate PIN
        }

    }

    //collect user-input
    public void enterUserName () {

        userLoginUI.promptUserName(); //prompt user for user name

        try {
            scanner = new Scanner(System.in);
            userName = scanner.nextLine();
        } catch (InputMismatchException e) {
            userLoginUI.displayUserNameInputMismatchException();
            userLoginUI.displayLogInMenu(); //display login menu
            logInMenuUserInput();           //listen for user input
        }

    }

    //collect user-input
    public void enterPIN () {

        userLoginUI.promptPIN(); //prompt user for PIN

        try {
            scanner = new Scanner(System.in);
            PIN = scanner.nextInt();
        } catch (InputMismatchException e) {
            userLoginUI.displayPINInputMismatchException();
            enterPIN(); // recursion, enter PIN again.
        }

    }

    //GETTERS & SETTERS:
    public static String getUserName () {
        return userName;
    }

    public static int getPIN () {
        return PIN;
    }

    public static int getAccountNumber() {
        return accountNumber;
    }

    public static void setAccountNumber(int accountNumber) {
        UserLogin.accountNumber = accountNumber;
    }

    public static double getBalance() {
        return balance;
    }

    public static void setBalance(double balance) {
        UserLogin.balance = balance;
    }


    public static double getBalanceFrmRegistration() {
        return balanceFrmRegistration;
    }

    public static void setBalanceFrmRegistration(double balanceFrmRegistration) {
        UserLogin.balanceFrmRegistration = balanceFrmRegistration;
    }

    public static int getPINFrmRegistration() {
        return PINFrmRegistration;
    }

    public static void setPINFrmRegistration(int PINFrmRegistration) {
        UserLogin.PINFrmRegistration = PINFrmRegistration;
    }

    public static int getAccountNumberFrmRegistration() {
        return accountNumberFrmRegistration;
    }

    public static void setAccountNumberFrmRegistration(int accountNumberFrmRegistration) {
        UserLogin.accountNumberFrmRegistration = accountNumberFrmRegistration;
    }

    public static String getUserNameFrmRegistration() {
        return userNameFrmRegistration;
    }

    public static void setUserNameFrmRegistration(String userNameFrmRegistration) {
        UserLogin.userNameFrmRegistration = userNameFrmRegistration;
    }

    public static boolean isLoginSuccess() {
        return loginSuccess;
    }



}
