package com.banking.service;

import com.banking.ui.UserAuthenticationUI;
import com.banking.util.UserDetailsLoader;

import java.util.HashMap;
import java.util.Scanner;

//import static com.banking.util.UserDetailsLoader.*;


public class UserAuthentication extends UserDetailsLoader { //connected to local database

    private static UserAuthenticationUI userAuthenticationUI;
    private static Scanner scanner;
    private static String currentUser;
    private static String currentUserFrmRegistration;


    private static final String DIRECTORY_PATH = "C:\\Users\\Jabez\\IdeaProjects\\banking-system8"; // Directory containing the user files

    //CONTRUCTOR:
    public UserAuthentication(UserAuthenticationUI userAuthenticationUI){
        UserAuthentication.userAuthenticationUI = userAuthenticationUI;
    }

    //===========================================//
    //LOGIN

    //METHOD
    boolean userNameAuthenticated = false;
    public boolean checkUserName() { //returns a boolean. True if username is registered. False if not registered.

        String userName = UserLogin.getUserName(); //get the user-inputted userName from the UserLogin class.
        String userNameFrmRegistration = UserLogin.getUserNameFrmRegistration(); //get the user-name after user registration

        //DEBUG
        //System.out.println("USER-INPUTTED USERNAME: "+userName);
        //System.out.println("USERNAME FROM REGISTRATION:  "+userNameFrmRegistration);


        currentUser = userName;
        currentUserFrmRegistration = userNameFrmRegistration;

        //check if the userName inputted exists in the user.txt files:
        for(HashMap.Entry<Integer, String> entry : accountNumberAndUserName.entrySet()) {

           /* //DEBUGGER:
            System.out.println(accountNumberAndUserName.keySet());
            System.out.println("Account Number: "+entry.getKey()+ ", User Name: " + entry.getValue());
            System.out.println("Final HashMap: " + accountNumberAndUserName);*/

            UserLogin.setAccountNumber(entry.getKey()); //set current user's account number, this is mostly for display purposes.
            checkBalance();                             //set current user's balance, to the balance found in user.txt files.

            //DEBUGGER:
            //System.out.println("USERNAME FROM REGISTRATION: "+currentUserFrmRegistration);

            if(entry.getValue().equals(currentUser) || userName.equals(currentUserFrmRegistration)) {
                userAuthenticationUI.userNameRegistered(); // Username is registered
                return userNameAuthenticated = true;       //break out of loop.
            }


//            if(entry.getValue().equals(currentUserFrmRegistration)) {
//                userAuthenticationUI.userNameRegistered(); // Username is registered
//                return userNameAuthenticated = true;       //break out of loop.
//            }


        }
        userAuthenticationUI.userNameNotRegistered();  // Username is not registered
        return userNameAuthenticated = false;

    }

    //METHOD
    public static void checkBalance() {

        //check if balance exists in the user.txt files:
        for(HashMap.Entry<Double, String> entry4 : balanceAndUserName.entrySet()) {

            UserLogin.setBalance(entry4.getKey());  //set current user's balance, to the balance found in user.txt files.

        }

    }

    //METHOD
    boolean pinAuthenticated = false;
    public boolean checkPIN() {


        int PIN = UserLogin.getPIN();               //get the user-inputted PIN from the UserLogin class.
        String userName = UserLogin.getUserName();  //get the user-inputted userName from the UserLogin class.
        currentUser = userName;                     //set current user to the userInputted userName.

        int pinFromRegistration = UserLogin.getPINFrmRegistration();

        //check if the pin inputted exists in the selected user.txt file
        for(HashMap.Entry<Integer, String> entry2 : pinAndUserName.entrySet()) {

            /*//DEBUGGER:
            System.out.println(pinAndUserName.keySet());
            System.out.println("PIN: "+entry2.getKey() + ", User Name: " + entry2.getValue());*/

            if((entry2.getValue().equals(currentUser) && entry2.getKey().equals(PIN)) && PIN != 0 || pinFromRegistration==PIN && PIN != 0) {
                System.out.println("PIN is correct!");
                return pinAuthenticated = true;
            }
        }

        userAuthenticationUI.incorrectPin();
        return pinAuthenticated = false;

    }

    //===========================================//
    //REGISTRATION

    boolean desiredUserNameAuthenticated = false;
    public boolean checkDesiredUserName() {

        //String userName = UserRegistration.getUserName().trim();
        String userName = UserRegistration.getUserName();
        currentUser = userName;

        //check if the userName inputted exists in the user.txt files
        for(HashMap.Entry<Integer, String> entry3 : accountNumberAndUserName.entrySet()) {

            if(entry3.getValue().equals(currentUser)) {
                userAuthenticationUI.desiredUserNameNotAvailable(); // Username already taken
                return desiredUserNameAuthenticated = false;    //break out of loop.
            }

        }

        //if userName already registered, either login or try registering another userName.
        userAuthenticationUI.desiredUserNameAvailable();
        return desiredUserNameAuthenticated = true;

    }

    boolean desiredPINAuthenticated = false;
    public boolean checkDesiredPIN() {

        int PIN1 = UserRegistration.getPIN();
        String PINStr = String.valueOf(PIN1);

        //HashMap<String, Integer> user = Account.getUser();

        if(PINStr.length()==6) {
            userAuthenticationUI.desiredPINAcceptable();
            desiredPINAuthenticated = true;
        } else {
            userAuthenticationUI.desiredPINUnacceptable();
            desiredPINAuthenticated = false;
        }

        return desiredPINAuthenticated;

    }




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
