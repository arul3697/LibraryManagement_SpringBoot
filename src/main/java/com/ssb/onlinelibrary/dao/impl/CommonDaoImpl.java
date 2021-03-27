package com.ssb.onlinelibrary.dao.impl;


import com.ssb.onlinelibrary.dao.CommonDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import static com.ssb.onlinelibrary.util.HibernateUtil.createSession;

@Repository
public class CommonDaoImpl implements CommonDao {

    public void saveOrUpdate(Object object) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(object);
        transaction.commit();
        session.close();

    }

    public void delete(Object object) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();

    }
}
