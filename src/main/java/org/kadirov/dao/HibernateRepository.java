package org.kadirov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateRepository {

    private final SessionFactory sessionFactory;

    public HibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <T> T execute(HibernateFunc<T> func){
        T result;

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        transaction.begin();

        try {
            result = func.execute(currentSession);
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }

        transaction.commit();
        return result;
    }

    protected void execute(HibernateAction action){
        execute(session -> {
            action.execute(session);
            return null;
        });
    }
}
