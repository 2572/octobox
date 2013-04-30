package com.vijayrc.octobox.repository;

import com.vijayrc.octobox.base.Db;
import static com.vijayrc.octobox.domain.LinkMail.*;
import com.vijayrc.octobox.domain.Mail;
import com.vijayrc.octobox.domain.Person;
import com.vijayrc.octobox.domain.Tag;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllMails {
    private Logger log = Logger.getLogger(AllMails.class);

    @Autowired
    private Db db;
    @Autowired
    private AllPersons allPersons;
    @Autowired
    private AllTags allTags;

    public Mail add(Mail mail) {
        Transaction tx = db.beginTx();
        try {
            Node mailNode = db.node();
            mailNode.setProperty("type", Mail.class.getName());
            mailNode.setProperty("subject", mail.subject().value());
            mailNode.setProperty("content", mail.content().value());
            mailNode.setProperty("time", mail.time().value());

            Node personNode = allPersons.add(mail.sender()).node();
            mailNode.createRelationshipTo(personNode, bySender);
            for (Person recipient : mail.recipients()) {
                personNode = allPersons.add(recipient).node();
                mailNode.createRelationshipTo(personNode, byReceiver);
            }
            Node tagNode;
            for (Tag tag : mail.tags()) {
                tagNode = allTags.add(tag).node();
                mailNode.createRelationshipTo(tagNode,byTag);
            }
            mail.node(mailNode);
            db.pass(tx);
            log.info("added:" + mail);
        } catch (Exception e) {
            db.fail(tx);
        }
        return mail;
    }

}
