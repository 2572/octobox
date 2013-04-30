package com.vijayrc.octobox.domain;

import org.neo4j.graphdb.Node;

public class Person {
    private String name;
    private String email;
    private Node node;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public Node node() {
        return node;
    }

    public Person node(Node node) {
        this.node = node;
        return this;
    }

    @Override
    public String toString() {
        return "Person[email=" + email + "|node=" + node + "]";
    }
}
