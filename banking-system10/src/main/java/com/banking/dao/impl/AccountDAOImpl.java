package com.banking.dao.impl;

import com.banking.dao.AccountDAO;
import com.banking.dao.TransactionDAO;
import com.banking.model.Account;
import com.banking.util.DbConnection;
import com.banking.util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImpl extends DbConnection implements AccountDAO {

    private QueryConstants queryConstants;

    //CONSTRUCTOR
    public AccountDAOImpl(QueryConstants queryConstants) {  //dependency injection
        this.queryConstants = queryConstants;
    }

    //CRUD

        //CREATE:

            //Upload user data to database:
            int insertCount;     //returns 1 if successful and 0 if not.
            @Override
            public void createAccount(Account account) { // Account class is passed as a parameter, to avoid passing multiple parameters: (e.g. String userName, int pin, double balance etc...)
                  //we use instance reference: (account.getUserName()) instead of class reference: (Account.getUserName()), This maintains correct OOP design principles (encapsulation, the state doesn't belong to class, but to the instance instead).
                  //e.g. LightBulb class has attribute String state = "Off". We want to set state to "On",  When we use class reference (static String state = "Off"), it can change the state for all classes using the lightbulb, e.g. carLight class, lamp class, fridgeLight class, all the lights here will turn off, if you use static keyword (And thats bad).

                try {
                    PreparedStatement insertPs = connection.prepareStatement(queryConstants.CREATE_USER);
                    insertPs.setInt(1, account.getAccountNumber());
                    insertPs.setString(2, account.getUserName());
                    insertPs.setInt(3, account.getPIN());
                    insertPs.setDouble(4, account.getBalance());
                    insertCount = insertPs.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

        //RETRIEVE:

            //RETRIEVE METHOD
            @Override
            public int retrieveUserID(String userName) {

                int userID = -1;    //return -1 if userID not found

                try {
                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_USER_ID);
                    ps.setString(1, userName);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) userID = rs.getInt(queryConstants.getUserIDColumn1());

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return userID;
            }

            //RETRIEVE METHOD
            //Retrieve userName
            @Override
            public boolean retrieveUsername(String userName) {

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_USERNAME);
                    ps.setString(1, userName);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) {  //while browsing database, compare if currently selected username from database equals user-inputted username
                        if(rs.getString(queryConstants.getUserNameColumn()).equals(userName)) {
                            return true; //userName exists.
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return false; //userName does not exist.
            }

            //RETRIEVE METHOD
            //Retrieve userName by accountNumber
            @Override
            public boolean retrieveUsernameByAccountNumber(int accountNumber) {

                try {
                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_USERNAME_BY_ACCOUNT_NUMBER);
                    ps.setInt(1, accountNumber);

                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) {
                        String userName =  rs.getString(queryConstants.getUserNameColumn());
                        if(userName != null && !userName.isEmpty()) { // Check if userName is not empty and not null
                            return true; //userName exists.
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //else:
                return false; //userName does not exist.
            }

            //RETRIEVE METHOD
            //Retrieve PIN by userName
            @Override
            public boolean retrievePIN(String userName, int PIN) {

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_PIN);
                    ps.setString(1, userName);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) {
                        if(rs.getInt(queryConstants.getPinColumn())==PIN) {
                            return true; //user-inputted PIN is correct
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return false; //user-inputted PIN is correct

            }

            //RETRIEVE METHOD
            //Retrieve account number by userName
            @Override
            public int retrieveAccountNumber(String userName) {

                int accountNumber = -1; //return -1 if account number not found

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_ACCOUNT_NUMBER);
                    ps.setString(1, userName);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) accountNumber = rs.getInt(queryConstants.getAccountNumberColumn());

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return accountNumber;
            }



            //RETRIEVE METHOD
            @Override
            public double retrieveBalance(String userName) {

                double balance = -1;

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_BALANCE);
                    ps.setString(1, userName);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) balance = rs.getDouble(queryConstants.getBalanceColumn());

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return balance;
            }

            //RETRIEVE METHOD
            @Override
            public double retrievePenalties(String userName) {

                double penalty = -1;

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_PENALTY);
                    ps.setString(1, userName);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) penalty = rs.getDouble(queryConstants.getPenaltiesColumn());

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return penalty;
            }

            //RETRIEVE METHOD
            @Override
            public double retrieveTransactionAmount(int userID) {

                double amount = -1;

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_AMOUNT);
                    ps.setInt(1, userID);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) amount = rs.getDouble(queryConstants.getAmountColumn());

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return amount;
            }

            //RETRIEVE METHOD
            @Override
            public String retrieveTransactionType(int userID) {

                String transactionType = "";

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_TRANSACTION_TYPE);
                    ps.setInt(1, userID);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) transactionType = rs.getString(queryConstants.getTransactionTypeColumn());

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return transactionType;
            }

            //RETRIEVE METHOD
            @Override
            public void retrieveTransactionHistory(int userID) {

                ResultSet rs1;
                ResultSet rs2;
                ResultSet rs3;

                PreparedStatement ps1;
                PreparedStatement ps2;
                PreparedStatement ps3;

                try {

                    ps1 = connection.prepareStatement(queryConstants.RETRIEVE_AMOUNT);
                    ps2 = connection.prepareStatement(queryConstants.RETRIEVE_TRANSACTION_TYPE);
                    ps3 = connection.prepareStatement(queryConstants.RETRIEVE_TRANSACTION_DATE_AND_TIME);
                    ps1.setInt(1, userID);
                    ps2.setInt(1, userID);
                    ps3.setInt(1, userID);

                    rs1 = ps1.executeQuery();
                    rs2 = ps2.executeQuery();
                    rs3 = ps3.executeQuery();

                    while (rs1.next() && rs2.next() && rs3.next()) {
                        double amount = rs1.getDouble(queryConstants.getAmountColumn());
                        String transactionType = rs2.getString(queryConstants.getTransactionTypeColumn());
                        String transactionDateAndTime = rs3.getString(queryConstants.getTransactionDateAndTimeColumn());

                        //System.out.println("|\t" + amount + transactionType + "\t|\t" + transactionDateAndTime + "\t|");
                        System.out.printf("|  %11.2f  |  %-17s  |  %-21s  |%n", amount, transactionType, transactionDateAndTime);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

            //RETRIEVE METHOD
            @Override
            public void retrieveTransactionDateAndTime(int userID) {

                //String transactionDateAndTime = "";

                try {

                    PreparedStatement ps = connection.prepareStatement(queryConstants.RETRIEVE_TRANSACTION_DATE_AND_TIME);

                    ps.setInt(1, userID);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()) {
                        String transactionDateAndTime = rs.getString(queryConstants.getTransactionDateAndTimeColumn());
                        System.out.println(transactionDateAndTime);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                //return transactionDateAndTime;
            }


    //UPDATE

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public int updateUserBalance(double result, String userName) {

        try {
            PreparedStatement updatePs = connection.prepareStatement(queryConstants.UPDATE_BALANCE);
            updatePs.setDouble(1, result);
            updatePs.setString(2, userName);

            return updatePs.executeUpdate();  //executeUpdate returns 1 if successful and 0 if not.;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int updatePenalties(double result, String userName) {

        try {
            PreparedStatement updatePs = connection.prepareStatement(queryConstants.UPDATE_PENALTY);
            updatePs.setDouble(1, result);
            updatePs.setString(2, userName);

            return updatePs.executeUpdate();  //executeUpdate returns 1 if successful and 0 if not.;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //DELETE

    @Override
    public void deleteAccountById(int id) {

    }


}
