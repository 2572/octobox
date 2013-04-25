package com.vijayrc.octobox.base;

import com.vijayrc.octobox.repository.AllMails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {

    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AllMails allMails = (AllMails) context.getBean("allMails");
        System.out.println(allMails);

    }
}
