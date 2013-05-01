package com.vijayrc.octobox.repository;

import com.vijayrc.octobox.base.Db;
import com.vijayrc.octobox.domain.Tag;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class AllTags {

    private Logger log = Logger.getLogger(AllTags.class);

    private Db db;
    private Index nameIndex;

    @Autowired
    public AllTags(Db db) {
        this.db = db;
        this.nameIndex = db.index("tag-name");
    }

    public Tag add(Tag tag){
        Transaction tx = db.beginTx();
        try {
            Node node = db.node();
            node.setProperty("type",Tag.class.getName());
            node.setProperty("name",tag.name());
            
            tag.node(node);
            db.pass(tx);
            log.info("added:" + tag);
        } catch (Exception e) {
            db.fail(tx);
        }
        return tag;
    }
}
