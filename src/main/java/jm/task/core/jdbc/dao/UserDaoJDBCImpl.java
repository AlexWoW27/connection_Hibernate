package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    @Override
    public void createUsersTable() {
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Person(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30), lastname varchar(30), age TINYINT)")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS Person")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement("INSERT INTO Person(name, lastName, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM Person WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM Person")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userList);
        return userList;
    }
    @Override
    public void cleanUsersTable() {
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement("TRUNCATE TABLE Person")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
