package ru.akirakozov.sd.refactoring.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductConnectionSource {
    private static final ProductConnectionSource instance = new ProductConnectionSource();

    public static ProductConnectionSource instance() {
        return instance;
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ProductConnectionSource() {
        try {
            try (Connection c = createConnection();
                 Statement stmt = c.createStatement()) {

                String sql = "CREATE TABLE IF NOT EXISTS PRODUCT" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " PRICE          INT     NOT NULL)";

                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
