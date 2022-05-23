package repository;

import Persoana.Sofer;
import Produs.Produs;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoferRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS soferi" +
                "(id int PRIMARY KEY AUTO_INCREMENT, nume varchar(30), prenume varchar(30), numarTelefon varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSofer(Sofer sofer) {
        String insertPersonSql = "INSERT INTO soferi(nume, prenume, numarTelefon) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, sofer.getNume());
            preparedStatement.setString(2, sofer.getPrenume());
            preparedStatement.setString(3, sofer.getNumarTelefon());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Sofer getSoferById(int id) {
        String selectSql = "SELECT * FROM soferi WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSofer(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Sofer> getSoferi() {
        String selectSql = "SELECT * FROM soferi";
        List<Sofer> soferList= new ArrayList<>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                soferList.add(mapToSofer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soferList;
    }

    public void updateSoferName(String name, int id) {
        String updateNameSql = "UPDATE soferi SET nume=? WHERE id=?";

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

    public void deleteSofer(int id) {
        String deleteSoferSql = "DELETE FROM soferi WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSoferSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private Sofer mapToSofer(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            return new Sofer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }

        return null;
    }
}
