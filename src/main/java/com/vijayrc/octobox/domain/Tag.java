package com.vijayrc.octobox.domain;

import org.neo4j.graphdb.Node;

public class Tag {
    private String name;
    private Node node;

    public String name() {
        return name;
    }

    public Tag name(String name) {
        this.name = name;
        return this;
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

    public static Tag copyNodeToTag(Tag tag, Node node) {
        if (node == null || tag == null) return tag;
        return tag.name((String) node.getProperty("name")).node(node);
    }

    public static Node copyTagToNode(Tag tag, Node node) {
        if (node == null || tag == null) return node;
        node.setProperty("type", Tag.class.getName());
        node.setProperty("name", tag.name());
        return node;
    }

    public boolean hasNode() {
        return node != null;
    }
}
