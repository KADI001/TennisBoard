package org.kadirov.dao;

import org.hibernate.Session;

public interface HibernateFunc<T> {
    T execute(Session session) throws Exception;
}
