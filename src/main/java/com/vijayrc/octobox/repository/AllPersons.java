package com.vijayrc.octobox.repository;

import com.vijayrc.octobox.base.Db;
import com.vijayrc.octobox.domain.Person;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class AllPersons {
    private Logger log = Logger.getLogger(AllPersons.class);

    private Db db;
    private Index<Node> nameIndex;
    private Index<Node> emailIndex;

    @Autowired
    public AllPersons(Db db) {
        this.db = db;
        this.nameIndex = db.index("person-name");
        this.emailIndex = db.index("person-email");
    }

    public Person add(Person person) {
        Transaction tx = db.beginTx();
        try {
            Person personDb = findByEmail(person.email());
            if (personDb.hasNode()) {
                db.pass(tx);
                return personDb;
            }
            Node node = db.node();
            Person.copyFromPersonToNode(person, node);

            nameIndex.add(node, "name", person.name());
            emailIndex.add(node, "email", person.email());

            db.pass(tx);
            log.info("added:" + person);
        } catch (Exception e) {
            log.error("error:" + person, e);
            db.fail(tx);
        }
        return person;
    }

    public Person findBy(String name, String email) {
        Person person = findByEmail(email);
        return person != null ? person : findByName(name);
    }

    public Person findByName(String name) {
        Node node = nameIndex.get("name", name).getSingle();
        log.info(message(node) + "|" + name);
        return Person.copyFromNodeToPerson(new Person(), node);
    }

    public Person findByEmail(String email) {
        Node node = emailIndex.get("email", email).getSingle();
        log.info(message(node) + "|" + email);
        return Person.copyFromNodeToPerson(new Person(), node);
    }

    private String message(Node node) {
        return (node == null) ? "not found" : "found";
    }


}
