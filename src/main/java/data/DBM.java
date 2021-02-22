package data;

import data.interfaces.IDBM;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBM implements IDBM {

    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/Assignment_3";
        try {
            // Establish the connection
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "02100964");

            return con;
        } catch (Exception e) {
            throw new ServerErrorException("Cannot connect to DB", 500);
        }
    }
}