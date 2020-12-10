package app.dao;

import app.HibernateConfiguration;
import app.entities.Authority;
import app.entities.UsersDataSet;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AuthorityDAO {

    private static AuthorityDAO instance;

    private AuthorityDAO() {
//        HibernateConfiguration.getInstance();
    }

    public static AuthorityDAO getInstance() {
        if (instance == null) {
            instance = new AuthorityDAO();
        }
        return instance;
    }

    public Authority getAuthByRole(String role) throws HibernateException {
        Session session = HibernateConfiguration.getInstance().openSession();
        Query query = session.createQuery("FROM Authority WHERE role='" + role + "'");
        Authority authority = (Authority) query.list().get(0);
        session.close();
        return authority;
    }
}