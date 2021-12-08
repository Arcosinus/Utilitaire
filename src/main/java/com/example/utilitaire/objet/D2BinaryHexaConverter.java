package com.example.utilitaire.objet;

public class D2BinaryHexaConverter {

    public static String dec2bin(String dec) {
        try {
            String bin = Integer.toBinaryString(Integer.parseInt(dec));
            return bin;
        }    catch (NumberFormatException e) {
            return "Please enter a valid number.";
        }
    }

    public static String bin2dec(String bin) {
        try {
            String dec = String.valueOf(Integer.parseInt(bin, 2));
            return dec;
        }    catch (NumberFormatException e) {
            return "Please enter a valid number.";
        }
    }

    public static String dec2hexa(String dec) {
        try {
            String hexa = Integer.toHexString(Integer.parseInt(dec));
            return hexa;
        }    catch (NumberFormatException e) {
            return "Please enter a valid number.";
        }
    }

    public static String hexa2dec(String hexa) {
        try {
        String dec = String.valueOf(Integer.parseInt(hexa,16));
        return dec;
        }    catch (NumberFormatException e) {
            return "Please enter a valid number.";
        }
    }
}
