package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoJDBCImpl();
    private UserDao userDaoHiber = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
//        userDao.createUsersTable();
        userDaoHiber.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
//        userDao.dropUsersTable();
        userDaoHiber.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        userDao.saveUser(name, lastName, age);
        userDaoHiber.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
//        userDao.removeUserById(id);
        userDaoHiber.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
//       System.out.println(userDao.getAllUsers());
//       return userDao.getAllUsers();
        System.out.println(userDaoHiber.getAllUsers());
        return userDaoHiber.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
//        userDao.cleanUsersTable();
        userDaoHiber.cleanUsersTable();
    }
}
