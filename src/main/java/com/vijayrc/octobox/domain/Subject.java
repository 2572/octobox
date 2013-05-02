package com.vijayrc.octobox.domain;

public class Subject {

    private String value;

    public Subject(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
