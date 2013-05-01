package com.vijayrc.octobox.base;

import com.vijayrc.octobox.domain.Config;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Db {
    private Logger log = Logger.getLogger(Db.class);
    private GraphDatabaseService databaseService;
    private Config config;

    @Autowired
    public Db(Config config) {
        this.config = config;
    }

    public Db start() {
        databaseService = new GraphDatabaseFactory().newEmbeddedDatabase(config.dataDir());
        log.info("db started");
        return this;
    }

    public Transaction beginTx(){
        return databaseService.beginTx();
    }

    public Db pass(Transaction tx){
        tx.success();
        tx.finish();
        return this;
    }

    public Db fail(Transaction tx){
        tx.failure();
        tx.finish();
        return this;
    }

    public Node node() {
        return databaseService.createNode();
    }

    public Index<Node> index(String name){
        return databaseService.index().forNodes(name);
    }

    public Db stop() {
        databaseService.shutdown();
        log.info("db stopped");
        return this;
    }

    public Db erase() throws Exception {
        stop();
        FileUtils.forceDelete(new File(config.dataDir()));
        log.info("db erased");
        return this;
    }

}
