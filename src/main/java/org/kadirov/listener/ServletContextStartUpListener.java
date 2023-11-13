package org.kadirov.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.kadirov.dao.*;
import org.kadirov.dao.match.ActiveMatchesDAO;
import org.kadirov.dao.match.H2ActiveMatchesDAO;
import org.kadirov.dao.match.MatchDAOImpl;
import org.kadirov.dao.player.H2PlayerScoreDAO;
import org.kadirov.dao.player.PlayerDAOImpl;
import org.kadirov.dao.player.PlayerScoreDAO;
import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.entity.MatchEntity;
import org.kadirov.entity.PlayerEntity;
import org.kadirov.entity.PlayerScoreEntity;
import org.kadirov.service.ActiveMatchService;
import org.kadirov.service.ActiveMatchServiceImpl;
import org.kadirov.service.MatchScoreService;
import org.kadirov.service.MatchScoreServiceImpl;

@WebListener
public class ServletContextStartUpListener implements ServletContextListener {

    public static final String HIBERNATE_CFG = "db/hibernate.cfg.xml";
    public static final String H2_HIBERNATE_CFG = "db/h2_hibernate.cfg.xml";

    private SessionFactory sessionFactory;
    private SessionFactory h2SessionFactory;
    private PlayerDAOImpl playerDAOImpl;
    private HibernateRepository hibernateRepository;
    private HibernateRepository h2HibernateRepository;
    private ActiveMatchesDAO h2ActiveMatchesDAO;
    private PlayerScoreDAO h2PlayerScoreDAO;
    private MatchDAOImpl matchDAOImpl;
    private ActiveMatchService activeMatchService;
    private MatchScoreService matchScoreService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        sessionFactory = new Configuration()
                .configure(HIBERNATE_CFG)
                .addAnnotatedClass(PlayerEntity.class)
                .addAnnotatedClass(MatchEntity.class)
                .buildSessionFactory();

        h2SessionFactory = new Configuration()
                .configure(H2_HIBERNATE_CFG)
                .addAnnotatedClass(ActiveMatchEntity.class)
                .addAnnotatedClass(PlayerScoreEntity.class)
                .addAnnotatedClass(PlayerEntity.class)
                .buildSessionFactory();

        hibernateRepository = new HibernateRepository(sessionFactory);
        h2HibernateRepository = new HibernateRepository(h2SessionFactory);
        playerDAOImpl = new PlayerDAOImpl(hibernateRepository);
        matchDAOImpl = new MatchDAOImpl(hibernateRepository);
        h2ActiveMatchesDAO = new H2ActiveMatchesDAO(h2HibernateRepository);
        h2PlayerScoreDAO = new H2PlayerScoreDAO(h2HibernateRepository);
        activeMatchService = new ActiveMatchServiceImpl(h2ActiveMatchesDAO);
        matchScoreService = new MatchScoreServiceImpl();

        servletContext.setAttribute("session", sessionFactory.getCurrentSession());
        servletContext.setAttribute("playerDAO", playerDAOImpl);
        servletContext.setAttribute("matchDAO", matchDAOImpl);
        servletContext.setAttribute("activeMatchesDAO", h2ActiveMatchesDAO);
        servletContext.setAttribute("playerScoreDAO", h2PlayerScoreDAO);
        servletContext.setAttribute("matchService", activeMatchService);
        servletContext.setAttribute("scoreService", matchScoreService);
    }

    private void initH2DataBaseTables(SessionFactory sessionFactory) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        transaction.begin();

        try{


            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sessionFactory.close();
    }
}
