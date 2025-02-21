package com.banking.service;

import com.banking.dao.AccountDAO;
import com.banking.dao.TransactionDAO;
import com.banking.dao.impl.AccountDAOImpl;
import com.banking.dao.impl.TransactionDAOImpl;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;
import com.banking.ui.BankSystemUI;
import com.banking.ui.UserAuthenticationUI;
import com.banking.ui.UserLoginUI;
import com.banking.ui.UserRegistrationUI;
import com.banking.util.QueryConstants;

import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankSystem {

    private static String currentUserName;      //track the current user's userName
    private static int currentUserAccNum;       //track the current user's accountNumber
    private static double currentUserBalance;   //track the current user's balance
    private static boolean userDoneRegister = false;

    private AccountDAO accountDAO;
    private QueryConstants queryConstants;
    private SavingsAccount savingsAcc;
    private CheckingAccount checkingAcc;
    private TransactionDAO transactionDAOImpl = new TransactionDAOImpl();

    //CONSTRUCTOR
    public BankSystem() {
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
        //AccountDAO accountDAO= new AccountDAOImpl(queryConstants);                                          //Interface instantiation. Use DAOImpl to access DAO.

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
        UserAuthenticationUI userAuthenticationUI = new UserAuthenticationUI();
        UserAuthentication userAuthentication = new UserAuthentication(userAuthenticationUI);

        //QueryConstants queryConstants1 = new QueryConstants();
        //AccountDAO accountDao = new AccountDAOImpl(queryConstants1);
        //bankSystemUI.displayMainMenu();

        greetUser(bankSystemUI);

        while (true) {
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
                        SendMoney(bankSystemUI, userAuthentication);
                        break;
                    case 4:
                        TransactionHistory();
                        break;
                    case 5:
                        AccountSettings(bankSystemUI);
                        break;
                    case 6:
                        System.out.println("PROGRAM EXITED");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("PLEASE ENTER A NUMBER\n");
                startMainMenu();
            }

        }

    }

    public void greetUser(BankSystemUI bankSystemUI) {

        System.out.println("\nWelcome!\nAccount Holder: " + currentUserName + "\n" + "Account no. " + currentUserAccNum + "\n"); //display Account info

    }

    //==============================================

    //SEND MONEY

    public void SendMoney(BankSystemUI bankSystemUI, UserAuthentication userAuthentication) {

        bankSystemUI.displaySendMoneyOptions(); //display options to send money, or return to main menu
        checkUserInputSendMoneyOptions();       //scan for user-input

        bankSystemUI.displaySendMoneyUserName();                    //display prompt to enter the user name we are sending money to.
        checkUserInputSendMoneyUserName(userAuthentication);

      /*  bankSystemUI.displaySendMoneyAccountNumber();               //display prompt to enter account number, or return to main menu
        checkUserInputSendMoneyAccountNumber(userAuthentication);    //scan for user-input
*/
        bankSystemUI.displayConfirmDetails();   //ask user if details are correct
        checkUserInputConfirmDetails();         //scan for user-input

        bankSystemUI.displaySendMoneyAmount();  //ask user to enter amount of money to send
        checkUserInputSendMoneyAmount();        //scan for user-input

        bankSystemUI.displayConfirmSendMoney();
        checkUserInputConfirmAmount();

        bankSystemUI.displayConfirmSendMoney();

        transferMoney();

    }

    public void checkUserInputSendMoneyOptions() {

        scanner = new Scanner(System.in);

        try {

            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    break;
                case 2:
                    this.startMainMenu();
                    break;
                default:
                    this.startMainMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
        }

    }

    int receiverAccountNumber;
    public void checkUserInputSendMoneyAccountNumber(UserAuthentication userAuthentication) {

        scanner = new Scanner(System.in);

        try {

            receiverAccountNumber = scanner.nextInt();

            if(receiverAccountNumber==1) {
                this.startMainMenu();
            }

            if(userAuthentication.checkAccountNumber(receiverAccountNumber)) {  //if true, user exists. If false user does not exist.
                //continues with the next method
            } else if (!userAuthentication.checkAccountNumber(receiverAccountNumber)) {
                System.out.println("User is not registered. Please try again");
                checkUserInputSendMoneyAccountNumber(userAuthentication);
            }

        } catch (Exception e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
            checkUserInputSendMoneyOptions();
        }

    }

    String receiverUserName;
    public void checkUserInputSendMoneyUserName(UserAuthentication userAuthentication) {

        scanner = new Scanner(System.in);

        try {
            receiverUserName = scanner.nextLine();

            if(receiverUserName.equals("1")) {
                this.startMainMenu();
            }

            if(userAuthentication.checkUserName(receiverUserName)) {

            } else {
                //System.out.println("User is not registered. Please try again");
                checkUserInputSendMoneyUserName(userAuthentication);
            }


        } catch (Exception e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
        }


    }


    public void checkUserInputConfirmDetails() {

        scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    this.startMainMenu();
                default:
                    System.out.println("Invalid choice. Please try again");
                    checkUserInputConfirmDetails();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid choice. Please try again!");
            checkUserInputConfirmDetails();
        }

    }

    double amount;
    public void checkUserInputSendMoneyAmount() {

        scanner = new Scanner(System.in);

        try {
            amount = scanner.nextDouble();

            if(amount==1) {
                this.startMainMenu();   //return to main menu
            }

        } catch (Exception e) {
            System.out.println("Invalid choice, please try again!");
            throw new RuntimeException(e);
        }


    }

    public void checkUserInputConfirmAmount() {

        scanner = new Scanner(System.in);

        System.out.println("Amount you entered: "+amount+"\n");

        try {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    this.startMainMenu();
                default:
                    System.out.println("Invalid choice. Please try again");
                    checkUserInputConfirmDetails();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid choice. Please try again!");
            checkUserInputConfirmDetails();
        }


    }

    public void transferMoney() {

        if((accountDAO.retrieveBalance(currentUserName)-amount)<0) {
            System.out.println("Insufficient balance! TRANSACTION CANCELLED");
            this.startMainMenu(); //go back to main menu
        }

        System.out.println("\nSuccessfully sent PHP"+amount+" sent to: "+receiverUserName+"\n");

        String transactionType1 = "Send Money";
        String transactionType2 = "Receive Money";

        //Updates sender balance and generate transaction history
        double result1 = (accountDAO.retrieveBalance(currentUserName)-amount);  //retrieve sender balance, subtract amount sent to other user, store in result
        double result2 = (accountDAO.retrieveBalance(receiverUserName)+amount);  //retrieve receiver balance, subtract amount sent to other user, store in result

        accountDAO.updateUserBalance(result1, currentUserName);                 //update sender balance
        accountDAO.updateUserBalance(result2, receiverUserName);                 //update receiver balance

        transactionDAOImpl.generateTransaction(accountDAO.retrieveUserID(currentUserName), amount, transactionType1);   //generate transaction history
        transactionDAOImpl.generateTransaction(accountDAO.retrieveUserID(receiverUserName), amount, transactionType2);   //generate transaction history

    }




    //==============================================

    //ACCOUNT SETTINGS

    public void AccountSettings(BankSystemUI bankSystemUI) {

        bankSystemUI.displayAccountSettings();

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        try {
            switch(choice) {
                case 1:
                    bankSystemUI.confirmLogOut();
                    checkUserInputConfirmLogOut(bankSystemUI);

                    break;
                case 2:
                    displayAccountInfo(bankSystemUI);
                    break;
                case 3:
                    this.startMainMenu();
                    break;
                default:
                    this.AccountSettings(bankSystemUI);
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
        }


    }

    public void checkUserInputConfirmLogOut(BankSystemUI bankSystemUI) {


        scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    //new BankSystem();
                    this.startUserLogin();
                    //startUserLogin();    //log-out user (go back to log-in)
                    break;
                case 2:
                    this.AccountSettings(bankSystemUI);  //return to accountSettings menu
                    break;
            }
        } catch (Exception e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
        }

    }

    public void displayAccountInfo(BankSystemUI bankSystemUI) {
        System.out.println("======ACCOUNT INFORMATION======\n");
        System.out.println("User name: "+currentUserName);
        System.out.println("Account number: "+currentUserAccNum);
        System.out.println("Your balance: PHP "+accountDAO.retrieveBalance(currentUserName));
        System.out.println("\nPress 1 to return\n");

        scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    this.AccountSettings(bankSystemUI);  //return to accountSettings menu
                    break;
                default:
                    this.AccountSettings(bankSystemUI);  //return to accountSettings menu
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
        }

    }



    public void TransactionHistory() {

        System.out.println("|==================TRANSACTION HISTORY==========================|");
        System.out.println("|   amount      |  transaction type   |     date and time       |");
        System.out.println("|---------------|---------------------|-------------------------|");
        accountDAO.retrieveTransactionHistory(accountDAO.retrieveUserID(currentUserName));
        System.out.println("\nPress 1 to return to Main Menu \n");

        scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    startMainMenu();
                    break;
                default:
                    startMainMenu();
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("\nPLEASE ENTER A VALUE\n");
            SavingsAccountMenu();
        }

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
