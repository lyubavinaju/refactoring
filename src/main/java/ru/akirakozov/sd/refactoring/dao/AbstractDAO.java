package ru.akirakozov.sd.refactoring.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractDAO<T> {
    Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    protected void executeUpdate(String sql) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
