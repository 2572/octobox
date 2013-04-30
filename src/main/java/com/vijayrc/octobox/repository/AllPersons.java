package com.vijayrc.octobox.repository;

import com.vijayrc.octobox.base.Db;
import com.vijayrc.octobox.domain.Person;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllPersons {
    private Logger log = Logger.getLogger(AllPersons.class);

    @Autowired
    private Db db;

    public Person add(Person person) {
        Transaction tx = db.beginTx();
        try {
            Node node = db.node();
            node.setProperty("name", person.name());
            node.setProperty("name", person.email());
            person.node(node);
            db.pass(tx);
            log.info("added:" + person);
        } catch (Exception e) {
            db.fail(tx);
        }
        return person;
    }

    public Person findBy(String name, String email) {
        Person person = findByEmail(email);
        return person != null ? person : findByName(name);
    }

    private Person findByName(String name) {
        return null;
    }

    private Person findByEmail(String email) {
        return null;
    }


}
