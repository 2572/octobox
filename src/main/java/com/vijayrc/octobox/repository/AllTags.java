package com.vijayrc.octobox.repository;

import com.vijayrc.octobox.base.Db;
import com.vijayrc.octobox.domain.Tag;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllTags {

    @Autowired
    private Db db;

    public void add(Tag tag){
        Transaction tx = db.beginTx();
        try {
            Node node = db.node();
            node.setProperty("name",tag.name());
            db.pass(tx);
        } catch (Exception e) {
            db.fail(tx);
        }
    }
}
