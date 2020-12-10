package app.dao;

import app.DBService.DBException;
import app.HibernateConfiguration;
import app.entities.Authority;
import app.entities.UsersDataSet;
import org.hibernate.*;
import java.util.List;


public class UsersDAO {
    private Transaction transaction;

    private static UsersDAO instance;

    private UsersDAO() {
//        HibernateConfiguration.getInstance();
    }

    public static UsersDAO getInstance() {
        if (instance == null) {
            instance = new UsersDAO();
        }
        return instance;
    }

    public long insertUser(UsersDataSet usersDataSet) throws HibernateException {
        Session session =  HibernateConfiguration.getInstance().openSession();
        transaction = session.beginTransaction();
        long id = (Long) session.save(usersDataSet);
        transaction.commit();
        session.close();
        return id;
    }

    public void edit(UsersDataSet usersDataSet) {
        Session session = HibernateConfiguration.getInstance().openSession();
        session.beginTransaction();
        session.update(usersDataSet);
        session.getTransaction().commit();
        session.close();
    }

    public List<UsersDataSet> getUsers() throws DBException {
        Session session = HibernateConfiguration.getInstance().openSession();
        Query query = session.createQuery("FROM UsersDataSet ORDER BY id ASC");
        List<UsersDataSet> users = query.list();
        session.close();
        return users;
    }

    public void delete(Long id) {
        Session session = HibernateConfiguration.getInstance().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM UsersDataSet WHERE id=" + id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


    public UsersDataSet get(long id) throws HibernateException {
        Session session =  HibernateConfiguration.getInstance().openSession();
        UsersDataSet usersDataSet = (UsersDataSet) session.get(UsersDataSet.class, id);
        session.close();
        return usersDataSet;
    }

    public UsersDataSet get(String login) throws HibernateException {
        Session session = HibernateConfiguration.getInstance().openSession();
        Query query = session.createQuery("FROM UsersDataSet WHERE login='" + login + "'");
        UsersDataSet usersDataSet = (UsersDataSet) query.list().get(0);
        session.close();
        return usersDataSet;
    }
}
