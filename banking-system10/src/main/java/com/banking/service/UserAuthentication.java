package com.banking.service;

import com.banking.dao.AccountDAO;
import com.banking.dao.impl.AccountDAOImpl;
import com.banking.ui.UserAuthenticationUI;
import com.banking.util.QueryConstants;

import java.util.HashMap;
import java.util.Scanner;

public class UserAuthentication { //connected to local database

    private UserAuthenticationUI userAuthenticationUI;
    private QueryConstants queryConstants = new QueryConstants();
    private AccountDAO accountDAO = new AccountDAOImpl(queryConstants);

    private static Scanner scanner;
    private static String currentUser;
    private static String currentUserFrmRegistration;


    private static final String DIRECTORY_PATH = "C:\\Users\\Jabez\\IdeaProjects\\banking-system8"; // Directory containing the user files

    //CONSTRUCTOR:
    public UserAuthentication(UserAuthenticationUI userAuthenticationUI){
        this.userAuthenticationUI = userAuthenticationUI;
        //this.accountDAO = accountDAO;

    }

    //===========================================//
    //LOGIN

    //METHOD
    //CHECK USERNAME
    public boolean checkUserName(String userName) {

        if(!accountDAO.retrieveUsername(userName)) {
            userAuthenticationUI.userNameNotRegistered();  //display message: userName is not registered
            return false;
        }

        userAuthenticationUI.userNameRegistered();    //display message: userName registered
        return true;    // userName available.

    }

    //METHOD
    //Retrieve account number by username
    public int retrieveAccountNumber(String userName) {

        return accountDAO.retrieveAccountNumber(userName);

    }

    //METHOD
    //CHECK PIN
    public boolean checkPIN(String userName, int PIN) {

        if(!accountDAO.retrievePIN(userName, PIN)) {
            userAuthenticationUI.incorrectPin();  //display message: incorrect PIN
            return false;
        }

        userAuthenticationUI.correctPin();    //display message: correct PIN
        return true;    // userName available.

    }

    //===========================================//
    //REGISTRATION

    //METHOD
    //Check if userName is available for registration. Returns true if available, false if not available.
    public boolean checkDesiredUserName(String userName) {

        if(accountDAO.retrieveUsername(userName)) {
            userAuthenticationUI.desiredUserNameNotAvailable();   //display message: userName already taken
            return false;
        }

        userAuthenticationUI.desiredUserNameAvailable();    //display message: userName available
        return true;    // userName available.

    }

    //METHOD
    //Checks if PIN inputted for registration has 6-digits. Returns true if user-inputted PIN has 6 digits, false if not.
    public boolean checkDesiredPIN(int PIN) {

        PIN = UserRegistration.getPIN();
        String PINStr = String.valueOf(PIN);    //convert int to String

        if(PINStr.length()==6) {                //check if the String length is 6
            userAuthenticationUI.desiredPINAcceptable(); //display message
            return true;
        } else {
            userAuthenticationUI.desiredPINUnacceptable();  //display message
            return false;
        }

    }

    //===========================================//
    //SEND MONEY

    public boolean checkAccountNumber(int accountNumber) {

        if(accountDAO.retrieveUsernameByAccountNumber(accountNumber)) { //if true, user exists. if false, user does not exist.
            userAuthenticationUI.userNameNotRegistered();  //display message: userName is not registered
            return false;
        }

        userAuthenticationUI.userNameRegistered();    //display message: userName registered
        return true;

    }

    //===========================================//






    //GETTERS & SETTERS:

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        UserAuthentication.currentUser = currentUser;
    }


    public static String getCurrentUserFrmRegistration() {
        return currentUserFrmRegistration;
    }

    public static void setCurrentUserFrmRegistration(String currentUserFrmRegistration) {
        UserAuthentication.currentUserFrmRegistration = currentUserFrmRegistration;
    }

}
