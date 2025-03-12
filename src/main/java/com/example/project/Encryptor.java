package com.example.project;


public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if (messageLen == 0) {
            return 1;
        }
        if (messageLen % rows == 0) {
            return messageLen/rows;
        }
        return (messageLen/rows) + 1;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] arr = new String[rows][determineColumns(message.length(), rows)];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (index < message.length()) {
                    arr[i][j] = message.substring(index, index + 1);
                    index++;
                }
                else {
                    arr[i][j] = "=";
                }
            }
        }
        return arr;
    }

    public static String encryptMessage(String message, int rows){
        String str = "";
        String[][] arr = Encryptor.generateEncryptArray(message, rows);
        for (int col = arr[0].length-1; col >= 0; col--) {
            for (int row = 0; row < arr.length; row++) {
                str += arr[row][col];
            }
        }
        return str;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String str = "";
        int newNumRows = Encryptor.determineColumns(encryptedMessage.length(), rows);
        String[][] arr = Encryptor.generateEncryptArray(encryptedMessage, newNumRows);
        for (int col = 0; col < arr[0].length; col++) {
            for (int row = arr.length-1; row >= 0; row--) {
                if (!arr[row][col].equals("=")) {
                    str += arr[row][col];
                }
            }
        }
        return str;
    }
}