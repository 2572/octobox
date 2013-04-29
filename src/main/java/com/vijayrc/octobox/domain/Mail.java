package com.vijayrc.octobox.domain;

import java.util.List;

public class Mail {
    private Subject subject;
    private Person sender;
    private List<Person> recipients;
    private List<Tag> tags;
    private Time time;
    private Content content;

    public Subject subject() {
        return subject;
    }

    public Person sender() {
        return sender;
    }

    public List<Person> recipients() {
        return recipients;
    }

    public List<Tag> tags() {
        return tags;
    }

    public Time time() {
        return time;
    }

    public Content content() {
        return content;
    }
}
