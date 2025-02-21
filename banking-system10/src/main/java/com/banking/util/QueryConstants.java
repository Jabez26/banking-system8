package com.banking.util;

public class QueryConstants {

    //TABLES:
    public final String usersTable = "users";
    public final String transactionHistoryTable = "transactionhistory";

    //COLUMNS:
        //users table columns:
        public final String userIDColumn1 = "userID";
        public final String userNameColumn = "userName";
        public final String accountNumberColumn = "accountNumber";
        public final String pinColumn = "PIN";
        public final String balanceColumn = "Balance";
        public final String penaltiesColumn = "penalties";

        //transactionhistory table columns:
        public final String userIDColumn2 = "userID";
        public final String amountColumn  = "amount";
        public final String transactionTypeColumn  = "transactionType";
        public final String transactionDateAndTimeColumn = "dateAndTime";

        //QUERIES:
            //CRUD:
                //CREATE:
                    //users table:
                    public final String CREATE_USER = "INSERT INTO "+usersTable+" ( "+accountNumberColumn+", "+userNameColumn+", "+pinColumn+", "+balanceColumn+" )"+" VALUES (?, ?, ?, ?)";

                    //transactionhistory table:
                    public final String GENERATE_TRANSACTION_HISTORY = "INSERT INTO "+transactionHistoryTable+" ( "+userIDColumn2+", "+amountColumn+", "+transactionTypeColumn+" ) "+" VALUES "+" (?, ?, ?) ";

                //RETRIEVE/READ:
                    //users table:
                    public final String RETRIEVE_USER_ID = "SELECT "+userIDColumn1+" FROM "+usersTable+ " WHERE "+userNameColumn+" = ?";
                    public final String RETRIEVE_USERNAME = "SELECT "+userNameColumn+" FROM "+usersTable+ " WHERE "+userNameColumn+" = ?";
                    public final String RETRIEVE_USERNAME_BY_ACCOUNT_NUMBER = "SELECT "+userNameColumn+" FROM "+usersTable+ " WHERE "+accountNumberColumn+" = ?";
                    public final String RETRIEVE_PIN = "SELECT "+pinColumn+" FROM "+usersTable+ " WHERE "+userNameColumn+" = ?";
                    public final String RETRIEVE_ACCOUNT_NUMBER = "SELECT "+accountNumberColumn+" FROM "+usersTable+" WHERE "+userNameColumn+" = ?";
                    public final String RETRIEVE_BALANCE = "SELECT "+balanceColumn+" FROM "+usersTable+" WHERE "+userNameColumn+" = ?";
                    public final String RETRIEVE_PENALTY = "SELECT "+penaltiesColumn+" FROM "+usersTable+" WHERE "+userNameColumn+" = ?";

                    //transactionhistory table:
                    public final String RETRIEVE_AMOUNT = "SELECT "+amountColumn+" FROM "+transactionHistoryTable+" WHERE "+userIDColumn2+" = ?";
                    public final String RETRIEVE_TRANSACTION_TYPE = "SELECT "+transactionTypeColumn+" FROM "+transactionHistoryTable+" WHERE "+userIDColumn2+" = ?";
                    public final String RETRIEVE_TRANSACTION_DATE_AND_TIME = "SELECT "+transactionDateAndTimeColumn+" FROM "+transactionHistoryTable+" WHERE "+userIDColumn2+" = ?";

                //UPDATE:
                public final String UPDATE_BALANCE = "UPDATE "+usersTable+" SET "+balanceColumn+" = ? "+" WHERE "+userNameColumn+" = ?";
                public final String UPDATE_PENALTY = "UPDATE "+usersTable+" SET "+penaltiesColumn+" = ? "+" WHERE "+userNameColumn+" = ?";

                //DELETE/ARCHIVE:



    //GETTERS:
    public String getUserIDColumn1() {
        return userIDColumn1;
    }

    public String getUserNameColumn() {
        return userNameColumn;
    }

    public String getPinColumn() {
        return pinColumn;
    }

    public String getAccountNumberColumn() {
        return accountNumberColumn;
    }

    public String getBalanceColumn() {
        return balanceColumn;
    }

    public String getPenaltiesColumn() {
        return penaltiesColumn;
    }


    public String getUserIDColumn2() {
        return userIDColumn2;
    }

    public String getAmountColumn() {
        return amountColumn;
    }

    public String getTransactionTypeColumn() {
        return transactionTypeColumn;
    }

    public String getTransactionDateAndTimeColumn() {
        return transactionDateAndTimeColumn;
    }

}
