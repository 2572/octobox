package com.vijayrc.octobox.domain;

import org.neo4j.graphdb.Node;

import java.util.List;

public class Mail {
    private Subject subject;
    private Person sender;
    private List<Person> recipients;
    private List<Tag> tags;
    private Time time;
    private Content content;
    private Node node;

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

    public Mail subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public Mail sender(Person sender) {
        this.sender = sender;
        return this;
    }

    public Mail recipients(List<Person> recipients) {
        this.recipients = recipients;
        return this;
    }

    public Mail tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Mail time(Time time) {
        this.time = time;
        return this;
    }

    public Mail content(Content content) {
        this.content = content;
        return this;
    }

    public Mail node(Node node) {
        this.node = node;
        return this;
    }

    public Node node() {
        return this.node;
    }

    @Override
    public String toString() {
        return "Mail[subject=" + subject + "|sender=" + sender + "|time=" + time + "|node=" + node + "]";
    }
}
