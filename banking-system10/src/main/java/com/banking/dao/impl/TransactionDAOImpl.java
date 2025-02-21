package com.banking.dao.impl;

import com.banking.dao.TransactionDAO;
import com.banking.util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.banking.util.DbConnection.connection;

public class TransactionDAOImpl implements TransactionDAO {

    QueryConstants queryConstants = new QueryConstants();

    @Override
    public void deposit(double amount, String userName) {

    }

    @Override
    public void withdraw(double amount, String userName) {

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
}
