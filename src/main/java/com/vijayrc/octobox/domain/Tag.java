package com.vijayrc.octobox.domain;

import org.neo4j.graphdb.Node;

public class Tag {
    private String name;
    private Node node;

    public Tag(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Node node() {
        return node;
    }

    public Tag node(Node node) {
        this.node = node;
        return this;
    }

    @Override
    public String toString() {
        return "Tag[name=" + name + "|node=" + node + "]";
    }
}
