package com.vijayrc.octobox.repository;

import com.vijayrc.octobox.base.Db;
import com.vijayrc.octobox.domain.Mail;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllMails {
    
    @Autowired
    private Db db;
    
    public void add(Mail mail){
        Transaction tx = db.beginTx();
        Node node = db.node();
        node.setProperty("subject",mail.subject().value());
        node.setProperty("content",mail.content().value());
        node.setProperty("subject",mail.subject().value());
        node.setProperty("subject",mail.subject().value());

    }
    
}
