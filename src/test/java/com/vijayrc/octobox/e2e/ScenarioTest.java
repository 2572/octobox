package com.vijayrc.octobox.e2e;

import com.vijayrc.octobox.base.Db;
import com.vijayrc.octobox.domain.*;
import com.vijayrc.octobox.repository.AllMails;
import com.vijayrc.octobox.repository.AllPersons;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ScenarioTest {

    @Autowired
    private Db db;
    @Autowired
    private AllMails allMails;
    @Autowired
    private AllPersons allPersons;

    @Before
    public void setup() {
        db.start();
    }

    @Test
    public void shouldAddAMailWithPersonAndTag() {
        Content content = new Content("content");
        Subject subject = new Subject("subj");
        Time time = new Time("2013-04-12 12:01:02");
        Mail mail = new Mail()
                .content(content)
                .subject(subject)
                .time(time)
                .sender(new Person("shravan", "shravan@outlook.com"))
                .tags(asList(new Tag("family")))
                .recipients(asList(new Person("vijay", "vijayrc@outlook.com"), new Person("rekha", "rekha@outlook.com")));

        allMails.add(mail);

    }

    @After
    public void tearDown() throws Exception {
        db.erase();
    }


}
