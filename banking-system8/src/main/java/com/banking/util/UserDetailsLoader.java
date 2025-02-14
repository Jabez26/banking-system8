package com.banking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class UserDetailsLoader {

    //should have private access modifier, but is set to public for faster prototyping, simplicity, and easy debugging.
    public static HashMap<Integer, String> accountNumbers = retrieveAccountNumbers();
    public static HashMap<Integer, String> accountNumberAndUserName = retrieveAccountNumberAndUserName();
    public static HashMap<Integer, String> pinAndUserName = retrievePINAndUserName();
    public static HashMap<Double, String> balanceAndUserName = retrieveBalanceAndUserName();

    private static final String DIRECTORY_PATH = "C:\\Users\\Jabez\\IdeaProjects\\banking-system8"; // Directory containing the user files
    public static int loadedAccountNumber = 0;
    public static String userName = "";
    public static int i = 0;

    public static HashMap<Integer, String> retrieveAccountNumberAndUserName() {
        HashMap<Integer, String> accountNumberAndUserName = new HashMap<>();
        HashSet<Integer> addedAccountNumbers = new HashSet<>();

        //Integer previousAccountNumber = -1; // Store the previous account number

        /*
        //short delay so file system has time to update. (doesnt actually work)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DIRECTORY_PATH), "Users*.txt")) {
            Iterator<Path> iterator = stream.iterator(); // Create an iterator for the DirectoryStream

            for (i = 0; iterator.hasNext(); i++) {
                Path entry = iterator.next();
                File file = entry.toFile();

              /*  //DEBUGGER:
                System.out.println("UserDetailsLoaderClass debugger: CURRENT FILE NAME: "+file.getName());
                System.out.println("current index i is: " + i);*/

                //convert fileReader to bufferedReader
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line; //represents each line of text in the user.txt file
                    loadedAccountNumber = 0; // Reset loadedAccountNumber for each file
                    userName = ""; // Reset userName for each file

                    while((line = reader.readLine()) != null) { //read each line in the user.txt file
                        if (line.startsWith("tempAccNum = ")) {
                            String accountNumberStr = line.substring("tempAccNum = ".length()); //basically, extract the string (int acc number) that comes after "tempAccNum = ".
                            loadedAccountNumber = Integer.parseInt(accountNumberStr);                 //convert string to int
                        }
                        if (line.startsWith("tempUserName = ")) {
                            userName = line.substring("tempUserName = ".length());  //extract the string (userName) that comes after "tempUserName = ".
                        }

                        // Skip adding entry if account number is 0 or equals the previous account number
                        //System.out.println("Previous number: " + loadedAccountNumber);
                        if (loadedAccountNumber != 0 && !addedAccountNumbers.contains(loadedAccountNumber)) {
                            accountNumberAndUserName.put(loadedAccountNumber, userName); // Place account number and file name inside the hashmap.
                            //DEBUGGER:
                            //System.out.println("loading account number in hashmap: " + loadedAccountNumber + " loading username in hashmap: " + userName);
                            addedAccountNumbers.add(loadedAccountNumber);
                        } else {
                            //DEBUGGER
                            //System.out.println("Skipping entry with account number 0 or account number equals previous account number.");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return accountNumberAndUserName;
    }


    public static double loadedBalance = 00.00;
    public static String userName1 = "";
    public static int k = 0;
    public static HashMap<Double, String> retrieveBalanceAndUserName() {
        HashMap<Double, String> accountBalanceAndUserName = new HashMap<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DIRECTORY_PATH), "Users*.txt")) {
            Iterator<Path> iterator = stream.iterator(); // Create an iterator for the DirectoryStream

            for (k = 0; iterator.hasNext(); k++) {
                Path entry = iterator.next();
                File file = entry.toFile();

                /*//DEBUGGER:
                System.out.println("UserDetailsLoaderClass debugger: CURRENT FILE NAME: "+file.getName());
                System.out.println("current index i is: " + k);*/

                //convert fileReader to bufferedReader
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line; //represents each line of text in the user.txt file
                    loadedBalance = 0; // Reset loadedAccountNumber for each file
                    userName = ""; // Reset userName for each file

                    while((line = reader.readLine()) != null) { //read each line in the user.txt file
                        if (line.startsWith("tempBalance = ")) {
                            String balanceSTR = line.substring("tempBalance = ".length()); //basically, extract the string (int acc number) that comes after "tempAccNum = ".
                            loadedBalance = Double.parseDouble(balanceSTR);                //convert string to int
                        }
                        if (line.startsWith("tempUserName = ")) {
                            userName = line.substring("tempUserName = ".length());         //extract the string (userName) that comes after "tempUserName = ".
                        }

                        // Skip adding entry if account number is 0 or equals the previous account number
                        //System.out.println("Previous number: " + loadedAccountNumber);
                        if (loadedBalance != 0) {
                            accountBalanceAndUserName.put(loadedBalance, userName); // Place account number and file name inside the hashmap.

                            //DEBUGGER
                            //System.out.println("loading balance in hashmap: " + loadedBalance + " loading username in hashmap: " + userName);
                        } else {
                            //DEBUGGER:
                            //System.out.println("Skipping entry with 0 balance");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return accountBalanceAndUserName;
    }

    public static HashMap<Integer, String> retrieveAccountNumbers() {
        HashMap<Integer, String> accountNumbers = new HashMap<>();

        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DIRECTORY_PATH), "Users*.txt");

            for (Path entry : stream) {
                File file = entry.toFile();

                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line; //represents each line of text in the user.txt file

                while((line = reader.readLine()) != null) { //read each line in the user.txt file

                    if(line.startsWith("tempAccNum = ")) {
                        String accountNumberStr = line.substring("tempAccNum = ".length()); //basically, extract the string (int acc number) that comes after "tempAccNum = ".
                        int accountNumber = Integer.parseInt(accountNumberStr);             //convert string to int
                        accountNumbers.put(accountNumber, file.getName());                  //place account number and file name inside the hashmap.

                    }

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return accountNumbers;
    }

    public static int loadedPIN = 0;
    public static String userName2 = "";
    public static int j = 0;
    public static HashMap<Integer, String> retrievePINAndUserName() {
        HashMap<Integer, String> pinAndUserName = new HashMap<>();
        Integer previousPIN = -1; //store the previous PIN

        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DIRECTORY_PATH), "Users*.txt");
            Iterator<Path> iterator = stream.iterator(); // Create an iterator for the DirectoryStream

            for (j = 0; iterator.hasNext(); j++) {
                int currentIndex = j;
                Path entry = iterator.next();
                File file = entry.toFile();

            /*    //DEBUGGER:
                System.out.println("UserDetailsLoaderClass debugger: CURRENT FILE NAME: "+file.getName());
                System.out.println("current index j is: " + currentIndex);*/

                BufferedReader reader = new BufferedReader(new FileReader(file));  //convert fileReader to bufferedReader
                String line; //represents each line of text in the user.txt file
                while((line = reader.readLine()) != null) { //read each line in the user.txt file
                    //retrieve data from txt files:
                    if(line.startsWith("tempPIN = ")) {
                        String pinSTR = line.substring("tempPIN = ".length());           //basically, extract the string (int pin) that comes after "tempAccNum = ".
                        loadedPIN = Integer.parseInt(pinSTR);                            //convert string PIN to int PIN
                    }
                    if(line.startsWith("tempUserName = ")) {
                        userName2 = line.substring("tempUserName = ".length());          //extract the string (userName) that comes after "tempUserName = ".
                    }

                    // Skip adding entry if PIN is 0 or equals the previous account number
                   /* //DEBUG:
                    System.out.println("Previous PIN: "+loadedPIN);*/


                    if (loadedPIN != 0 && loadedPIN!=previousPIN) {
                        pinAndUserName.put(loadedPIN, userName2); // Place account number and file name inside the hashmap.

                        //DEBUGGER:
                        //System.out.println("loading PIN in hashmap: " + loadedPIN + " loading username in hashmap: " + userName);
                        previousPIN = loadedPIN; // Update the previous account number
                    } else {
                        //DEBUGGER:
                        //System.out.println("Skipping entry with PIN = 0 or PIN equals previous PIN.");
                    }
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pinAndUserName;
    }


}
