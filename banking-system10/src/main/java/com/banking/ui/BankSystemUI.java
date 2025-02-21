package com.banking.ui;

public class BankSystemUI {

    public void displayMainMenu() {

        System.out.println("MAIN MENU: ");

    }

    public void displayMainMenuOptions() {

        System.out.println("======MAIN MENU======");
        System.out.println("Enter a number: " +
                "\n\t1. Savings Account" +
                "\n\t2. Checking Account" +
                "\n\t3. Send Money" +
                "\n\t4. Transaction History" +
                "\n\t5. Account Settings" +
                "\n\t6. Exit"+"\n"
        );

    }

    //==============================================

    //SEND MONEY

    public void displaySendMoneyOptions() {
        System.out.println("======SEND MONEY======");
        System.out.println(
                        "\n\t1. Send Money"+
                        "\n\t2. Return to main menu"
        );
    }

    public void displaySendMoneyUserName() {
        System.out.println("======EXPRESS SEND======\n");
        System.out.println("Enter user name to send money to: "+
                "\nOr enter 1 to return to main menu\n"
        );
    }

    public void displaySendMoneyAccountNumber() {
        System.out.println("======EXPRESS SEND======\n");
        System.out.println("Enter account number: "+
                "\nOr enter 1 to return to main menu\n"
        );
    }

    public void displaySendMoneyAmount() {
        System.out.println("Enter amount: "+
                "\nOr enter 1 to return to main menu\n"
        );
    }

    public void displayConfirmSendMoney() {
        System.out.println("\nIs the amount you entered correct?\n"+
                "\t1. Yes\n"+
                "\t2. No, return to main menu\n");
    }

    public void displayConfirmDetails() {
        System.out.println("\nAre the details you entered correct?\n"+
                "\t1. Yes\n"+
                "\t2. No, return to main menu\n");

    }

    public void displaySendMoneyError() {
        System.out.println("This user does not exist. Please try again");
    }

    //==============================================

    //ACCOUNT SETTINGS

    public void displayAccountSettings() {
        System.out.println("======ACCOUNT SETTINGS======");
        System.out.println("\nPlease select a number: " +
                " \n 1: Log Out" +
                " \n 2: Account information"+
                " \n 3: Return to main menu"
        );
    }

    public void confirmLogOut() {
        System.out.println("Are you sure you want to log out?");
        System.out.println("1. Yes\n2. No");
    }

    //==============================================

    public void greetUser() {

        System.out.println("\nWelcome!");

    }


}
