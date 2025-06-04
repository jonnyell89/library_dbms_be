package com.example.library_dbms_be.enums;

public enum Status {

    OPEN("open"),
    OVERDUE("overdue"),
    CLOSED("closed");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
