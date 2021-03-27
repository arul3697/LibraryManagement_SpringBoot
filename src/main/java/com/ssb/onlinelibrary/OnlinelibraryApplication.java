package com.ssb.onlinelibrary;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;

import static com.ssb.onlinelibrary.util.HibernateUtil.createSession;
/*
 * @author Arulselvan S
 */
@SpringBootApplication
public class OnlinelibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinelibraryApplication.class, args);
        System.out.println("Welcome Online Library");

    }

}
