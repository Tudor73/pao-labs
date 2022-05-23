package repository;

import Persoana.User;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users" +
                "(id int PRIMARY KEY AUTO_INCREMENT, nume varchar(30), prenume varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(User u) {
        String insertPersonSql = "INSERT INTO users(nume, prenume) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, u.getNume());
            preparedStatement.setString(2, u.getPrenume());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String selectSql = "SELECT * FROM users WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<User> getUsers() {
        String selectSql = "SELECT * FROM users";
        List<User> userlist= new ArrayList<>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userlist.add(mapToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    public void updateUserName(String name, int id) {
        String updateNameSql = "UPDATE users SET nume=? WHERE id=?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(int id) {
        String deleteUserSql = "DELETE FROM users WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private User mapToUser(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        }

        return null;
    }
}
