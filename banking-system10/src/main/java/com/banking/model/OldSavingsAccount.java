/*
package com.banking.model;

import com.banking.dao.AccountDAO;
import com.banking.dao.TransactionDAO;
import com.banking.util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.banking.util.DbConnection.connection;

public class OldSavingsAccount extends Account implements TransactionDAO {

    //Properties:
    String accountType = "Savings Account";
    public double interestRate = 0.0625; //interest rate per year
    public double minBalanceToEarn = 500; //minimum balance to earn interest.
    public double minimumBalance = 2000.00; //if account falls below this, maintaining balance is deducted to the account.
    public double maintainingBalance = 500.00; //Penalty. subtracted if account is below minimum balance.

    private String userName;
    private double balance;
    private AccountDAO accountDAO;
    private QueryConstants queryConstants;

    //CONSTRUCTOR
    public OldSavingsAccount(String userName, AccountDAO accountDAO, QueryConstants queryConstants) { //AccountDAO, dependency injected
        this.userName = userName;
        this.accountDAO = accountDAO;
        this.queryConstants = queryConstants;
    }

    //displayAccountInfo() inherited from Account class.
    //@Override
    void displayAccountInfo() {
        int accountNumber = getAccountNumber();
        String accountHolder = getUserName();
        double balance = getBalance();
        System.out.println(this.accountType+"\n"+"Account number: "+accountNumber+"\n"+"Account holder: "+accountHolder+"\n"+"Balance: "+balance+"\n");
    }

    //deposit and withdraw method implemented from Transaction interface

    //METHOD
    //Retrieve balance, deposit computation, update balance.
    @Override
    public void deposit(double amount, String userName) {
        System.out.println("You deposited PHP"+amount+"\n");
        String transactionType = "Savings deposit";

        //Deposit, update user balance, generate transaction history
        double result = (accountDAO.retrieveBalance(userName)+amount);  //retrieve user balance, add amount, store in result
        accountDAO.updateUserBalance(result, userName);
        generateTransaction(accountDAO.retrieveUserID(userName), amount, transactionType);  //generate transaction history
    }

    //METHOD
    //Retrieve balance, withdraw computation, update balance.
    @Override
    public void withdraw(double amount, String userName) {

        if(checkIfSufficientBalance(amount, userName)) {  //if true:
            return; //break out of method
        }

        //else if false: continue withdrawal

        System.out.println("You withdrew PHP" + amount + "\n");
        String transactionType = "Savings withdraw";

        //Withdraw, update user balance, generate transaction history
        double result = (accountDAO.retrieveBalance(userName) - amount);  //retrieve user balance. subtract amount, store in result
        accountDAO.updateUserBalance(result, userName);                 //update user balance
        generateTransaction(accountDAO.retrieveUserID(userName), amount, transactionType);  //generate transaction history

    }

    @Override
    public void generateTransaction(int userID, double amount, String transactionType) {

        try {

            PreparedStatement insertPs = connection.prepareStatement(queryConstants.GENERATE_TRANSACTION_HISTORY);
            insertPs.setInt(1, userID);
            insertPs.setDouble(2, amount);
            insertPs.setString(3, transactionType);

            insertPs.executeUpdate();    //returns 1 if successful and 0 if not

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean penalized = false;
    public boolean insufficientBalance = false;

    public void accountRemarks(String userName){
        double userBalance = accountDAO.retrieveBalance(userName);
        double outstandingPenalty = accountDAO.retrievePenalties(userName);

        //Calculate
        //double totalRequiredDeposit = minimumBalance - (userBalance + outstandingPenalty); //if negative, user will incur penalties, and has to overcome minimum balance.
        double totalRequiredDeposit = (minimumBalance + outstandingPenalty) - userBalance; //if negative, user will incur penalties, and has to overcome minimum balance.

        // Reset insufficientBalance flag if the user now has sufficient balance
        if(userBalance >= minimumBalance) {
            insufficientBalance = false;
        }

        if(!penalized && !insufficientBalance) {
            System.out.println("No remarks");
        }


        //Penalize user for going below minimum balance:
        if(userBalance<minimumBalance && !penalized){
            System.out.println("You are below minimum balance, deduction of PHP"+maintainingBalance+" incurred");
            penalized = true;   //mark user as penalized. Penalizes user once, and not everytime he visits withdraw or deposit.
            penalize(userName);
        }

        //check if user is penalized (user has not paid penalty yet)
        if(penalized) {
            //debug message (u can comment-out the sout line below):
            System.out.println("You have an outstanding penalty of PHP"+outstandingPenalty+" auto-deduction once balance is above minimum of 2000");
            penalize(userName);
        }

        //Display the amount needed to reach the minimum balance and pay off penalties
        if(totalRequiredDeposit > 0) {  //will only display if totalRequiredDeposit is not negative.
            System.out.println("To avoid further penalties, please deposit a total of PHP " + totalRequiredDeposit +
                    " to bring your balance to the minimum required balance and pay off any outstanding penalties.");
        }

        if(insufficientBalance) {
            System.out.println("Insufficient balance! Please refill balance.");
        }

        //Revoke interest earning for going below minimum balance:
        if(userBalance<minBalanceToEarn){
            System.out.println("You are below minimum balance. Interest earning revoked");
        }
    }

    public boolean checkIfSufficientBalance(double amount, String userName) {
        double userBalance = accountDAO.retrieveBalance(userName);

        //check if the result of money withdrawal will result to negative balance.
        if((userBalance-amount)<0) {
            System.out.println("Insufficient balance! Please top-up your balance");
            return true;    //insufficient balance
        }

        return false;   //sufficient balance

    }



    public void penalize(String userName) {
        double userBalance = accountDAO.retrieveBalance(userName);

        //auto-deduct penalty only if balance is sufficient:
        if(userBalance-maintainingBalance>0) {
            String transactionType = "Penalty";

            //Pay penalty, update balance, generate transaction history:
            double penaltyResult = userBalance-maintainingBalance;
            accountDAO.updateUserBalance(penaltyResult, userName);
            accountDAO.updatePenalties(0, userName);
            generateTransaction(accountDAO.retrieveUserID(userName), penaltyResult, transactionType);

            penalized = false; //penalty already paid, reset penalized user to false;

            //else, if balance not sufficient to be penalized, penalize later:
        } else {
            System.out.println("Insufficient balance, penalty will be automatically deducted when balance is sufficient");
            accountDAO.updatePenalties(maintainingBalance, userName);
            insufficientBalance = true;
        }

    }


}
*/
