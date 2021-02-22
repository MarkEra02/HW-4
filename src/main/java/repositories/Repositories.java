package repositories;

import data.interfaces.IDBM;
import entities.Med;
import repositories.interfaces.IRepositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Repositories implements IRepositories {
    private final IDBM dbManager;

    public Repositories(IDBM dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Med> searchMedByName(String name) {
        Connection connection = null;

        try {
            connection = dbManager.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Meds WHERE name LIKE '%" + name + "%'");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Med> Meds = new LinkedList<>();

            while (resultSet.next()) {
                Med Med = new Med(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getString("manufacturer"));

                Meds.add(Med);
            }

            return Meds;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Med getMedById(int id) {
        Connection connection = null;

        try {
            connection = dbManager.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Meds WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Med Med = new Med();

            if (resultSet.next()) {
                Med.setId(resultSet.getInt("id"));
                Med.setName(resultSet.getString("name"));
                Med.setPrice(resultSet.getDouble("price"));
                Med.setExpiration_date(resultSet.getDate("expirationDate").toLocalDate());
                Med.setManufacturer(resultSet.getString("manufacturer"));
            }

            return Med;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addMed(Med Med) {
        Connection connection = null;

        try {
            connection = dbManager.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Meds (name, price, expiration_date, manufacturer) VALUES(?,?,?,?)");

            preparedStatement.setString(1, Med.getName());
            preparedStatement.setDouble(2, Med.getPrice());
            preparedStatement.setDate(3, Date.valueOf(Med.getExpiration_date()));
            preparedStatement.setString(4, Med.getManufacturer());

            preparedStatement.execute();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeMedById(int id) {
        Connection connection = null;

        try {
            connection = dbManager.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Meds WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
