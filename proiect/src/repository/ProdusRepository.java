package repository;

import Local.Local;
import Persoana.Sofer;
import Produs.Produs;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdusRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS produse" +
                "(id int PRIMARY KEY AUTO_INCREMENT, nume varchar(30), tip varchar(30), pret double, greutate double, local_id int," +
                "FOREIGN KEY (local_id) REFERENCES localuri(local_id) ";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProdus(Produs produs) {
        String insertPersonSql = "INSERT INTO produse(nume, tip, pret, greutate, local_id) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, produs.getNume());
            preparedStatement.setString(2, produs.getTip());
            preparedStatement.setDouble(3, produs.getPret());
            preparedStatement.setDouble(4, produs.getGreutate());
            preparedStatement.setInt(5, produs.getIdRestaurant());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Produs getProdusById(int id) {
        String selectSql = "SELECT * FROM produse WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProdus(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Produs> getProduse() {
        String selectSql = "SELECT * FROM produse";
        List<Produs> produsList= new ArrayList<>();

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produsList.add(mapToProdus(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produsList;
    }

    public void updateProdusName(String name, int id) {
        String updateNameSql = "UPDATE produse SET nume=? WHERE id=?";

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
    public void deleteProdus(int id) {
        String deleteProdusSql = "DELETE FROM produse WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteProdusSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Produs mapToProdus(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            return new Produs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4),
                    resultSet.getDouble(5), resultSet.getInt(6));
        }

        return null;
    }
}
