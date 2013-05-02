package com.vijayrc.octobox.domain;

public class Content {

    private String value;

    public Content(String value) {
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
