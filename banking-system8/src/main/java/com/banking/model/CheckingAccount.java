package com.banking.model;

import com.banking.service.Transaction;

import static com.banking.service.UserLogin.getAccountNumber;
import static com.banking.service.UserLogin.getUserName;

public class CheckingAccount extends Account implements Transaction {

    //Properties:
    String accountType = "Checking Account";
    public double minimumBalance = 5000.00; //if account falls below this, maintaining balance is deducted to the account.
    public double maintainingBalance = 5000.00; //subtracted if account is below minimum balance.

    //CONSTRUCTOR
    /*
    public CheckingAccount(int accountNumber, String userName, int pin) {
        super(accountNumber, userName, pin);
    }
     */

   /* @Override
    void displayAccountInfo() {
        //String accountNumber = getAccountNumber();
        //String accountHolder = getAccountHolder();
        double balance = getBalance();
        //System.out.println(this.accountType+"\n"+"Account number: "+accountNumber+"\n"+"Account holder: "+accountHolder+"\n"+"Balance: "+balance+"\n");
    }

    @Override
    public double deposit(double amount) {
        return 0;
    }

    @Override
    public double withdraw(double amount) {
        return 0;
    }*/

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
        if(this.getBalance()<minimumBalance){
            System.out.println("You are below minimum balance, deduction of PHP"+maintainingBalance+" incurred\n");
            this.setBalance(this.getBalance()-maintainingBalance);
        }
        System.out.println("You withdrew PHP"+amount+"\n");
        return this.setBalance(this.getBalance()-amount);
    }

    public void AccountRemarks(){
        if(this.getBalance()<minimumBalance){
            System.out.println("You are below minimum balance, deduction of PHP"+maintainingBalance+" incurred");
            this.setBalance(this.getBalance()-maintainingBalance);
        }
    }
}
