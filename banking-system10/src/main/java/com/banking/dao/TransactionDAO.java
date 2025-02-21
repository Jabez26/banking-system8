package com.banking.dao;

public interface TransactionDAO {

    void deposit(double amount, String userName);

    void withdraw(double amount, String userName);

    //void accountRemarks();
    void generateTransaction(int userID, double amount, String transactionType);


}
