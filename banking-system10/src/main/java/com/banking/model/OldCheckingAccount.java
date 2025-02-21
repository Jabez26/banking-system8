/*
package com.banking.model;

import com.banking.dao.AccountDAO;
import com.banking.dao.TransactionDAO;
import com.banking.util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.banking.util.DbConnection.connection;

public class OldCheckingAccount extends Account implements TransactionDAO {

    //Properties:
    String accountType = "Checking Account";
    public double minimumBalance = 5000.00; //if account falls below this, maintaining balance is deducted to the account.
    public double maintainingBalance = 5000.00; //subtracted if account is below minimum balance.

    private String userName;
    private double balance;
    private AccountDAO accountDAO;
    private QueryConstants queryConstants;

    //CONSTRUCTOR
    public OldCheckingAccount(String userName, AccountDAO accountDAO, QueryConstants queryConstants) { //AccountDAO & TransactionDAO, dependency injected
        this.userName = userName;
        this.accountDAO = accountDAO;
        this.queryConstants = queryConstants;
    }

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
        String transactionType = "Checking deposit";
        generateTransaction(accountDAO.retrieveUserID(userName), amount, transactionType);  //generate transaction history
        double result = (accountDAO.retrieveBalance(userName)+amount);  //retrieve user balance, add amount, store in result
        accountDAO.updateUserBalance(result, userName);
    }


    //METHOD
    //Retrieve balance, withdraw computation, update balance.
    @Override
    public void withdraw(double amount, String userName) {
        System.out.println("You withdrew PHP"+amount+"\n");
        String transactionType = "Checking withdraw";
        generateTransaction(accountDAO.retrieveUserID(userName), amount, transactionType);  //generate transaction history
        double result = (accountDAO.retrieveBalance(userName)-amount);  //retrieve user balance. subtract amount, store in result
        accountDAO.updateUserBalance(result, userName);                 //update user balance
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

    public void accountRemarks(String userName){
        if(this.getBalance()<minimumBalance){
            System.out.println("You are below minimum balance, deduction of PHP"+maintainingBalance+" incurred");
            this.setBalance(accountDAO.retrieveBalance(userName)-maintainingBalance);
        }
    }
}
*/
