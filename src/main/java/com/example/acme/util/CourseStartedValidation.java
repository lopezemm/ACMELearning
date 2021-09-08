package com.example.acme.util;

public enum CourseStartedValidation {
Y, N;

public static boolean contains(String test) {
        for (CourseStartedValidation c : CourseStartedValidation.values()) {
        if (c.name().toString().equalsIgnoreCase(test)) {
        return true;
        }
        }
        return false;
        }
}
