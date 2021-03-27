package com.ssb.onlinelibrary;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;

import static com.ssb.onlinelibrary.util.HibernateUtil.createSession;

@SpringBootApplication
public class OnlinelibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinelibraryApplication.class, args);
        System.out.println("Welcome Online Library");
//       Session session = createSession();
//  session.beginTransaction();
//
//  String SQL_QUERY = "SELECT COUNT(*) FROM user name";
//  Query query = session.createQuery(SQL_QUERY);
//
//  for(Iterator it = query.iterate(); it.hasNext();)
//  {
//   long row = (Long) it.next();
//   System.out.print("Count: " + row);
//  }
//  session.getTransaction().commit();
//  session.close();

    }

}
