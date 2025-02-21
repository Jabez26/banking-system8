package com.banking.util;

import java.util.HashSet;
import java.util.Random;

public class AccountNumberGenerator {

    private static int uniqueAccountNum;

    public static int generateUniqueNumbers() {

        //int[] array = {1, 1, 2, 2, 3, 3, 4, 4, 5};
        //               ^  ^  ^  ^  ^  ^  ^  ^  ^
        //index i or j:  0  1  2  3  4  5  6  7  8

        Random random = new Random();
        int arraySize = 9;

        int[] array = new int[arraySize];
        boolean allNumUnique = false;

        //Generate an array of random (not unique) numbers:
        for(int i = 0; i<arraySize; i++){
            array[i]=random.nextInt(9)+1;
        }

        /* This block of code prints out the duplicate numbers.
        System.out.println("\n--------------------------------------");
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if(array[i]==array[j]){
                    System.out.println("Indices i: " + i + " & j: " + j + " are duplicate numbers");
                }
            }
        }
        System.out.println("\n--------------------------------------");
         */

        //Detect duplicates and replace with a random number until array is unique:
        while(!allNumUnique){ //keep looping until all numbers are unique.
            allNumUnique = true;
            HashSet<Integer> seen = new HashSet<>();
            for (int i = 0; i < array.length; i++) {
                if (seen.contains(array[i])) {
                    array[i] = random.nextInt(9) + 1;
                    allNumUnique = false;
                } else {
                    seen.add(array[i]);
                }
            }
        }

        //Prints out an array of integers as a single integer.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        String accountNum = sb.toString();
        uniqueAccountNum = Integer.parseInt(accountNum);

        return uniqueAccountNum;

    }

}
