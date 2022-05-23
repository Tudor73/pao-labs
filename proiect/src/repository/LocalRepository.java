package repository;

import Local.*;
import Persoana.User;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS localuri" +
                "(local_id int PRIMARY KEY AUTO_INCREMENT, nume varchar(30), program varchar(30), adresa varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertLocal(Local local) {
        String insertPersonSql = "INSERT INTO localuri(nume, program, adresa) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, local.getNumeLocal());
            preparedStatement.setString(2, local.getProgram());
            preparedStatement.setString(3, local.getAdresa());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Local getLocalById(int id) {
        String selectSql = "SELECT * FROM localuri WHERE local_id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToRestaurant(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Local> getLocaluri() {
        String selectSql = "SELECT * FROM localuri";
        List<Local> localsList= new ArrayList<>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                localsList.add(mapToRestaurant(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localsList;
    }

    public void updateLocalName(String name, int id) {
        String updateNameSql = "UPDATE localuri SET nume=? WHERE local_id=?";

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
    public void deleteLocal(int id) {
        String deleteLocalSql = "DELETE FROM localuri WHERE local_id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteLocalSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Local mapToRestaurant(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            return new Restaurant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }

        return null;
    }
}
