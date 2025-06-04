package com.example.library_dbms_be.enums;

public enum Availability {

    AVAILABLE("available"),
    UNAVAILABLE("unavailable");

    private final String value;

    Availability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
