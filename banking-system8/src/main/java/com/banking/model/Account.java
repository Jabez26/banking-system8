package com.banking.model;

public abstract class Account {

    /*
    private static HashMap<String, Integer> user = new HashMap<>(); //A list of specific users (userName, PIN)
    private static HashMap<String, Integer> users = new HashMap<>(); //A list of all users (userName, accountNumber)

    //For debugging:
    private static String tempUserName = "ADMIN";
    private static int tempAccNum = 123456789;
    private static int tempPIN = 112233;
    private static double tempBalance = 50000.00;

    //For debugging:
    private static String tempUserName2 = "";
    private static int tempAccNum2;
    private static int tempPIN2;
    private static double tempBalance2 = 696969.00;
     */

    /*private final int accountNumber;
    private final String userName;
    private final int PIN;*/

    private double balance = 50000.00;

    //CONSTRUCTOR:
    protected Account() {

    }

    //ABSTRACT METHODS
    abstract void displayAccountInfo();

    //abstract void addUser();

    //GETTERS & SETTERS:

    /*
    //Hashmap for all USERS:
    public static HashMap<String, Integer> getUsers() {
        return users;
    }

    public static void setUsers(HashMap<String, Integer> users) {
        Account.users = users;
    }

    //Hashmap for specific USER:
    public static HashMap<String, Integer> getUser() {
        return user;
    }

    public static void setUser(HashMap<String, Integer> users) {
        Account.user = users;
    }
    */

    public double getBalance() {
        return balance;
    }

    public double setBalance(double balance) {
        this.balance = balance;
        return balance;
    }

    /*
    //GETTERS for temporary / debugging values:
    public static String getTempUserName() {
        return tempUserName;
    }

    public static int getTempPIN() {
        return tempPIN;
    }

    public static String getTempUserName2() {
        return tempUserName2;
    }

    public static int getTempPIN2() {
        return tempPIN2;
    }

    public static void setTempUserName2(String tempUserName2) {
        Account.tempUserName2 = tempUserName2;
    }

    public static void setTempAccNum2(int tempAccNum2) {
        Account.tempAccNum2 = tempAccNum2;
    }

    public static void setTempPIN2(int tempPIN2) {
        Account.tempPIN2 = tempPIN2;
    }

    public static int getTempAccNum() {
        return tempAccNum;
    }

    public static int getTempAccNum2() {
        return tempAccNum2;
    }

    public static double getTempBalance() {
        return tempBalance;
    }

    public static double getTempBalance2() {
        return tempBalance2;
    }

    public static void setTempBalance(double tempBalance) {
        Account.tempBalance = tempBalance;
    }

    public static void setTempBalance2(double tempBalance2) {
        Account.tempBalance2 = tempBalance2;
    }

    */

}
