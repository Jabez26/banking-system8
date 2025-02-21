package com.banking.service;

import com.banking.dao.AccountDAO;
import com.banking.dao.impl.AccountDAOImpl;
import com.banking.model.Account;
import com.banking.ui.UserAuthenticationUI;
import com.banking.ui.UserRegistrationUI;
import com.banking.util.AccountNumberGenerator;
import com.banking.util.QueryConstants;

import java.util.InputMismatchException;
import java.util.Scanner;

//UserRegistration Class:
// - Scans for user input
// - validate user input
// - interact with AccountDAOImpl to create/add/register new users.

public class UserRegistration {

    private UserRegistrationUI userRegistrationUI;
    private UserAuthenticationUI userAuthenticationUI;
    private UserAuthentication userAuthentication;
    private QueryConstants queryConstants = new QueryConstants();
    private AccountDAO accountDAO = new AccountDAOImpl(queryConstants);

    private static Scanner scanner;
    private static String userName;             //store user-input
    private static int PIN;                     //store user-input

    public static boolean registrationSuccess = false;

    //CONSTRUCTOR
    public UserRegistration(UserRegistrationUI userRegistrationUI, AccountDAO accountDAO, UserAuthenticationUI userAuthenticationUI, UserAuthentication userAuthentication) {   //dependency injection
        this.userRegistrationUI = userRegistrationUI;
        this.accountDAO = accountDAO;
        this.userAuthenticationUI = userAuthenticationUI;
        this.userAuthentication = userAuthentication;

        register();   //start registration

        registrationSuccess = true;
    }


    public void register() {

        authenticateDesiredUserName();  //1st method to get executed

        authenticateDesiredPIN();       //2nd

        createAccount();                //3rd and last

    }

    //METHOD
    //Check if userName is available:
    public void authenticateDesiredUserName() {
        boolean isUserNameAvailable = false;

        while(!isUserNameAvailable) {
            enterDesiredUserName();     //ask for user-input (userName)
            isUserNameAvailable = userAuthentication.checkDesiredUserName(userName);    //returns true or false. True (userName available), false (userName taken).
        }

    }

    //METHOD
    //Check if PIN is acceptable:
    public void authenticateDesiredPIN() {
        boolean isPINAcceptable = false;

        while(!isPINAcceptable) {
            enterDesiredPIN();      //ask for user-input (PIN)
            isPINAcceptable =  userAuthentication.checkDesiredPIN(PIN);    //returns true or false. True (PIN has 6-digits), false (PIN does not have 6-digits)
        }

    }

    //METHOD
    //Asks for user-input (userName)
    public void enterDesiredUserName() {

        userRegistrationUI.promptUserName();

        try {
            scanner = new Scanner(System.in);
            userName = scanner.nextLine();
        } catch (InputMismatchException e) {
            userRegistrationUI.desiredUserNameInputMismatch();
        }

    }

    //METHOD
    //Asks for user-input (PIN)
    public void enterDesiredPIN() {

        userRegistrationUI.promptPIN();

        try {
            scanner = new Scanner(System.in);
            PIN = scanner.nextInt();
        } catch (InputMismatchException e) {
            userRegistrationUI.desiredPINInputMismatch();
        }

    }

    //NEW create account method
    public void createAccount() {

        Account account = new Account();    //create new Account instance
        account.setAccountNumber(AccountNumberGenerator.generateUniqueNumbers());   //generate unique account number
        account.setUserName(userName);
        account.setPIN(PIN);

        accountDAO.createAccount(account); //upload user data to database
        userRegistrationUI.displayRegistrationSuccessful();

    }

    //GETTERS

    public static int getPIN() {
        return PIN;
    }

    public static String getUserName() {
        return userName;
    }



}
