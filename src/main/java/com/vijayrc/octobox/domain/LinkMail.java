package com.vijayrc.octobox.domain;


import org.neo4j.graphdb.RelationshipType;

public enum LinkMail implements RelationshipType {
    bySubject, byPerson, byTag, byTopic
}