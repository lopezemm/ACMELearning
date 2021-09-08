package com.example.acme.util;

public class UtilValidations {

    public static boolean isStarted(String isStarted){
        if("S".equalsIgnoreCase(isStarted))
            return true;
        return false;
    }
}
