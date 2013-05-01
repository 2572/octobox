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
    private Index<Node> index;

    @Autowired
    public AllTags(Db db) {
        this.db = db;
        this.index = db.index("tag-name");
    }

    public Tag add(Tag tag) {
        Transaction tx = db.beginTx();
        try {
            Tag tagDb = findBy(tag.name());
            if (tagDb.hasNode()) {
                db.pass(tx);
                log.info("exists:" + tag);
                return tagDb;
            }
            Node node = db.node();
            Tag.copyTagToNode(tag, node);
            index.add(node, "name", tag.name());

            db.pass(tx);
            log.info("added:" + tag);
        } catch (Exception e) {
            db.fail(tx);
            log.error("error:" + tag + "|" + e);
        }
        return tag;
    }

    public Tag findBy(String name) {
        Node node = index.get("name", name).getSingle();
        log.info("found " + node + " for " + name);
        return Tag.copyNodeToTag(new Tag(), node);
    }
}
