package com.example.acme.util;

import java.util.Arrays;
import java.util.List;

public class UtilValidations {

    //private static final List<String> roles =  Arrays.asList("ADMIN", "USER");
    private static final List<String> roles =  Arrays.asList("INSTR", "STD");


    public static boolean isStarted(String isStarted){
        if("S".equalsIgnoreCase(isStarted))
            return true;
        return false;
    }

        public static boolean validateRole(String role){
        if (role != null && !role.isEmpty()) {
            return roles.stream().anyMatch(role::equalsIgnoreCase);
        }
        return false;
    }
}
