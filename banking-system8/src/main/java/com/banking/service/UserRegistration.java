package com.banking.service;

import com.banking.ui.UserAuthenticationUI;
import com.banking.ui.UserRegistrationUI;
import com.banking.util.AccountNumberGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserRegistration {

    private static UserRegistrationUI userRegistrationUI;

    private static Scanner scanner;
    private static String userName;
    private static int PIN;
    private static int accountNumber;
    private static double balance = 50000.00;

    public static boolean registrationSuccess = false;

    //CONSTRUCTOR
    public UserRegistration(UserRegistrationUI userRegistrationUI) {
        UserRegistration.userRegistrationUI = userRegistrationUI;

        UserAuthenticationUI userAuthenticationUI = new UserAuthenticationUI();                 //setup UI
        UserAuthentication userAuthentication = new UserAuthentication(userAuthenticationUI);   //setup authentication logic

        register(userAuthentication);   //start registration
        System.out.println("REGISTRATION SUCCESS!");
        registrationSuccess = true;
    }

    public void register(UserAuthentication userAuthentication) {

        authenticateDesiredUserName(userAuthentication);

        authenticateDesiredPIN(userAuthentication);

        registerUser();

        //DEBUG CODE:
        //System.out.println("USERNAME FROM REGISTRATION:" + userName);

    }

    public void authenticateDesiredUserName(UserAuthentication userAuthentication) {

        while(!userAuthentication.desiredUserNameAuthenticated) {
            enterDesiredUserName();
            userAuthentication.checkDesiredUserName();
        }

    }

    public void authenticateDesiredPIN(UserAuthentication userAuthentication) {

        while(!userAuthentication.desiredPINAuthenticated) {
            enterDesiredPIN();
            userAuthentication.checkDesiredPIN();
        }

    }

    public void enterDesiredUserName() {

        userRegistrationUI.promptUserName();

        try {
            scanner = new Scanner(System.in);
            userName = scanner.nextLine();
        } catch (InputMismatchException e) {
            userRegistrationUI.desiredUserNameInputMismatch();
        }

    }

    public void enterDesiredPIN() {

        userRegistrationUI.promptPIN();

        try {
            scanner = new Scanner(System.in);
            PIN = scanner.nextInt();
        } catch (InputMismatchException e) {
            userRegistrationUI.desiredPINInputMismatch();
        }

    }

    public void registerUser() {

        //HashMap<String, Integer> user = Account.getUser();
        //HashMap<String, Integer> users = Account.getUsers();

        //int accountNum = AccountNumberGenerator.generateUniqueNumbers(); //generate unique account number

        //Code for debugging:
        //Account.setTempUserName2(userName);
        //Account.setTempPIN2(PIN);
        //Account.setTempAccNum2(accountNum);

        generateUserDetailsTxtFile();


    }

    //generate a user txt file for registration:
    private static int autoIncrement = 0;           //gets incremented after every successful registration.
    //String userNum = String.valueOf(autoIncrement);
    public static void generateUserDetailsTxtFile() {

        //String fileName = "Users"+userNum+".txt"; //auto generate a filename.

        //create a txt file for a new user:
        File file;
        //file = new File(fileName);

        do {
            String fileName = "Users"+autoIncrement+".txt"; //auto generate a filename.
            file = new File(fileName);
            System.out.println("Checking file: " + fileName); // Debug statement. Checks current file iteration

            if(file.exists()) {
                //DEBUG
                //System.out.println("File name already exists");
                autoIncrement++; //file name already exists, try a different file number to use.

                //DEBUG
                //System.out.println("File exists. Incrementing autoIncrement to " + autoIncrement); // Debug statement
            }

        } while (file.exists());

        //else if file name does not exist yet, create a new file.
        try {
            file.createNewFile();
            System.out.println("File created: "+ file.getName()); //create a new file for user details.
            //DEBUG
            //System.out.println("Registration success!");
            autoIncrement++; //this number gets appended to the Users.txt file. e.g. Users1.txt, Users2.txt.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        accountNumber = AccountNumberGenerator.generateUniqueNumbers(); //generate unique account number

        try {
            FileWriter writer = new FileWriter(file.getName());
            writer.write("tempUserName = "+ userName +
                    "\ntempAccNum = " + accountNumber +
                    "\ntempPIN = " + PIN +
                    "\ntempBalance = " + balance
            );
            //DEBUG:
            //System.out.println("File "+file.getName()+" updated");
            writer.close(); //always close the file writer

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    //GETTERS

    public static int getPIN() {
        return PIN;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getAccountNumber() {
        return accountNumber;
    }

    public static double getBalance() {
        return balance;
    }

}
