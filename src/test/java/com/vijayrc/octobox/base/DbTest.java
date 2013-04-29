package com.vijayrc.octobox.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DbTest {

    @Autowired
    private Db db;

    @Test
    public void shouldCreateANodeAndShutDown() throws Exception {
        Transaction tx = db.start().beginTx();
        Node node = db.node();
        assertNotNull(node);
        db.pass(tx);
        db.erase();
    }


}
