/*
package com.banking.service;

import com.banking.dao.AccountDAO;
import com.banking.dao.TransactionDAO;
import com.banking.dao.impl.AccountDAOImpl;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;
import com.banking.ui.BankSystemUI;
import com.banking.ui.UserAuthenticationUI;
import com.banking.ui.UserLoginUI;
import com.banking.ui.UserRegistrationUI;
import com.banking.util.QueryConstants;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OldBankingSystem {

    private static String currentUserName;      //track the current user's userName
    private static int currentUserAccNum;       //track the current user's accountNumber
    private static double currentUserBalance;   //track the current user's balance
    private static boolean userDoneRegister = false;

    private AccountDAO accountDAO;
    private QueryConstants queryConstants;
    private SavingsAccount savingsAcc;
    private CheckingAccount checkingAcc;
    private TransactionDAO transactionDAO;

    //CONSTRUCTOR
    public OldBankingSystem() {
        this.queryConstants = new QueryConstants();
        this.accountDAO = new AccountDAOImpl(queryConstants);
        this.savingsAcc = new SavingsAccount(currentUserName, accountDAO, queryConstants);
        this.checkingAcc = new CheckingAccount(currentUserName, accountDAO, queryConstants);
        startUserLogin();
    }

    //METHOD
    public void startUserLogin() {

        UserLoginUI userLoginUI = new UserLoginUI();        //setup UI
        new UserLogin(userLoginUI);                         //setup login logic

        //retrieve current user's data:
        //(path: Account class > UserAuthentication class > UserLogin class > BankSystem class)

        currentUserName = UserLogin.getUserName();          //retrieve the current user's userName
        currentUserAccNum = UserLogin.getAccountNumber();   //retrieve the current user's accountNumber
        currentUserBalance = UserLogin.getBalance();        //retrieve the current user's balance

        if(UserLogin.loginSuccess) {        //if boolean loginSuccess = true. Proceed to main menu
            UserLogin.loginSuccess = false; //reset flag. Avoid bugs.
            startMainMenu();                //start main menu
        }

        if(UserLogin.attemptRegistration) {         //if boolean attemptRegistration = true. Proceed to registration
            UserLogin.attemptRegistration = false;  //reset flag. Avoid bugs.
            userDoneRegister = true;                //flag for chaging account number in main menu.
            startUserRegistration();                //start registration
        }

    }

    //METHOD
    public void startUserRegistration() {

        UserAuthenticationUI userAuthenticationUI = new UserAuthenticationUI();
        UserAuthentication userAuthentication = new UserAuthentication(userAuthenticationUI);
        QueryConstants queryConstants = new QueryConstants();
        AccountDAO accountDAO= new AccountDAOImpl(queryConstants);                                          //Interface instantiation. Use DAOImpl to access DAO.

        UserRegistrationUI userRegistrationUI = new UserRegistrationUI();                                   //setup UI
        new UserRegistration(userRegistrationUI, accountDAO, userAuthenticationUI, userAuthentication);     //setup registration logic

        if(UserRegistration.registrationSuccess) {          //if boolean registrationSuccess = true. Proceed to login
            UserRegistration.registrationSuccess = false;   //reset flag. Avoid bugs.
            startUserLogin();                               //start login
        }

    }

    //===========================================//
    //MAIN MENU

    //METHOD
    private static Scanner scanner;
    public void startMainMenu() {

        BankSystemUI bankSystemUI = new BankSystemUI();
        //bankSystemUI.displayMainMenu();

        greetUser(bankSystemUI);
        bankSystemUI.displayMainMenuOptions();
        scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("SAVINGS ACCOUNT: ");
                    SavingsAccountMenu();
                    break;
                case 2:
                    System.out.println("CHECKING ACCOUNT: ");
                    CheckingAccountMenu();
                    break;
                case 3:
                    System.out.println("PROGRAM EXITED");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("PLEASE ENTER A NUMBER");
            startMainMenu();
        }

    }

    public void greetUser(BankSystemUI bankSystemUI) {

        System.out.println("\nWelcome!\nAccount Holder: " + currentUserName + "\n" + "Account no. " + currentUserAccNum + "\n"); //display Account info

      */
/*  //bankSystemUI.greetUser();
        if(userDoneRegister) {
            //currentUserAccNum = UserRegistration.getAccountNumber();
            userDoneRegister = false; //reset flag
        } else if (!userDoneRegister) {

            System.out.println("\nWelcome!\nAccount Holder: " + currentUserName + "\n" + "Account no. " + currentUserAccNum + "\n"); //display Account info

        }

        //System.out.println("\nWelcome!\nAccount Holder: " + currentUserName + "\n" + "Account no. " + currentUserAccNum + "\n"); //display Account info
*//*

    }


    public void SavingsAccountMenu(){
        System.out.println("Savings Current Balance: PHP"+accountDAO.retrieveBalance(currentUserName));
        System.out.println("Enter a number: " +
                "\n\t1. Withdraw" +
                "\n\t2. Deposit" +
                "\n\t3. Return"+"\n"
        );

        System.out.println("Remarks:");
        savingsAcc.accountRemarks(currentUserName);     //display account remarks


        scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("Enter amount to withdraw: ");
                    double amountWithdraw = scanner.nextInt();
                    savingsAcc.withdraw(amountWithdraw, currentUserName);
                    startMainMenu();
                    break;
                case 2:
                    System.out.println("Enter amount to deposit: ");
                    double amountDeposit = scanner.nextInt();
                    savingsAcc.deposit(amountDeposit, currentUserName);
                    startMainMenu();
                    break;
                case 3:
                    startMainMenu();
                    break;
                default: break;
            }

        } catch (InputMismatchException e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
            SavingsAccountMenu();
        }
    }

    public void CheckingAccountMenu(){
        System.out.println("Checking Current Balance: PHP"+accountDAO.retrieveBalance(currentUserName));
        System.out.println("Enter a number: " +
                "\n\t1. Withdraw" +
                "\n\t2. Deposit" +
                "\n\t3. Return"+"\n"
        );

        System.out.println("Remarks:");
        checkingAcc.accountRemarks(currentUserName);   //display account remarks
        scanner = new Scanner(System.in);
        try {

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter amount to withdraw: ");
                    double amountWithdraw = scanner.nextInt();
                    checkingAcc.withdraw(amountWithdraw, currentUserName);
                    startMainMenu();
                    break;
                case 2:
                    System.out.println("Enter amount to deposit: ");
                    double amountDeposit = scanner.nextInt();
                    checkingAcc.deposit(amountDeposit, currentUserName);
                    startMainMenu();
                    break;
                case 3:
                    startMainMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
            CheckingAccountMenu();
        }
    }

}
*/
