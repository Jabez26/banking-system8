package com.banking.dao;

import com.banking.model.Account;

public interface AccountDAO {

    //CRUD

    //Create:
    void createAccount(Account account);

    //Retrieve:
    int retrieveUserID(String userName);
    boolean retrieveUsername(String userName);
    boolean retrieveUsernameByAccountNumber(int accountNumber);
    boolean retrievePIN(String userName, int PIN);
    int retrieveAccountNumber(String userName);
    double retrieveBalance(String userName);
    double retrievePenalties(String userName);

    double retrieveTransactionAmount(int userID);
    String retrieveTransactionType(int userID);
    void retrieveTransactionDateAndTime(int userID);


    void retrieveTransactionHistory(int userID);


    //Update:
    void updateAccount(Account account);
    int updateUserBalance(double result, String userName);
    int updatePenalties(double result, String userName);

    //Delete/archive:
    void deleteAccountById(int id);


}
