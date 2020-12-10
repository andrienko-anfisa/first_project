package app.DBService;

import app.dao.UsersDAO;
import app.entities.Authority;
import app.entities.UsersDataSet;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public class DBService {
    private static DBService instance;
    public UsersDAO usersDAO;

    private DBService() {
        this.usersDAO = UsersDAO.getInstance();
    }

    public static DBService getInstance() {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    public long addUser(UsersDataSet usersDataSet) throws DBException {
        try {
            return usersDAO.insertUser(usersDataSet);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public List<UsersDataSet> getAllUsers() throws DBException {
        return usersDAO.getUsers();
    }


    public void deleteUser(Long id) {
        usersDAO.delete(id);
    }

    public void editUser(UsersDataSet usersDataSet) {
        usersDAO.edit(usersDataSet);
    }

    public UsersDataSet getUserById(long id) throws DBException {
        try {
            return usersDAO.get(id);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public UsersDataSet getUserByLogin(String login) throws DBException {
        try {
            return usersDAO.get(login);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

}
