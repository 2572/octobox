package com.vijayrc.octobox.domain;

import org.neo4j.graphdb.Node;

public class Person {
    private String name;
    private String email;
    private Node node;

    public Person() {
    }

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
    
    public static Node copyFromPersonToNode(Person person, Node node){
        if(person == null || node == null) return node;
        node.setProperty("type", Person.class.getName());
        node.setProperty("name", person.name());
        node.setProperty("email", person.email());
        return node;
    }

    public static Person copyFromNodeToPerson(Person person, Node node){
        if(person == null || node == null) return person;
        return person.name((String) node.getProperty("name")).email((String) node.getProperty("email")).node(node);
    }

    private Person email(String email) {
        this.email = email;
        return this;
    }

    private Person name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Person[email=" + email + "|node=" + node + "]";
    }

    public boolean hasNode() {
        return node != null;
    }
}
