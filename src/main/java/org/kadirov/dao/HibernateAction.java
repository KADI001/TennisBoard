package org.kadirov.dao;

import org.hibernate.Session;

public interface HibernateAction {
    void execute(Session session) throws Exception;
}
