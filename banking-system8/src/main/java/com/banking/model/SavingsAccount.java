package com.banking.model;

import com.banking.service.Transaction;

import static com.banking.service.UserLogin.getAccountNumber;
import static com.banking.service.UserLogin.getUserName;

public class SavingsAccount extends Account implements Transaction {

    //Properties:
    String accountType = "Savings Account";
    public double interestRate = 0.0625; //interest rate per year
    public double minBalanceToEarn = 5000; //minimum balance to earn interest.
    public double minimumBalance = 2000; //if account falls below this, maintaining balance is deducted to the account.
    public double maintainingBalance = 2000.00; //subtracted if account is below minimum balance.

 /*   //CONSTRUCTOR
    public SavingsAccount(int accountNumber, String userName, int pin) {
        super(accountNumber, userName, pin);
    }*/

   /* @Override
    void displayAccountInfo() {

    }

    @Override
    public double deposit(double amount) {
        return 0;
    }

    @Override
    public double withdraw(double amount) {
        return 0;
    }
    */

    //displayAccountInfo() inherited from Account class.
    @Override
    void displayAccountInfo() {
        int accountNumber = getAccountNumber();
        String accountHolder = getUserName();
        double balance = getBalance();
        System.out.println(this.accountType+"\n"+"Account number: "+accountNumber+"\n"+"Account holder: "+accountHolder+"\n"+"Balance: "+balance+"\n");
    }

    //deposit and withdraw method implemented from Transaction interface
    @Override
    public double deposit(double amount) {
        System.out.println("You deposited PHP"+amount+"\n");
        return this.setBalance(this.getBalance()+amount);
    }

    @Override
    public double withdraw(double amount) {
        System.out.println("You withdrew PHP"+amount+"\n");
        return this.setBalance(this.getBalance()-amount);
    }

    public void AccountRemarks(){
        if(this.getBalance()<minimumBalance){
            System.out.println("You are below minimum balance, deduction of PHP"+maintainingBalance+" incurred");
            this.setBalance(this.getBalance()-maintainingBalance);
        }
        if(this.getBalance()<minBalanceToEarn){
            System.out.println("You are below minumum balance. Interest earning revoked");
        }
    }


}
