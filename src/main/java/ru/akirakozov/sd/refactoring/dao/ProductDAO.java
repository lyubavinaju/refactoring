package ru.akirakozov.sd.refactoring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ru.akirakozov.sd.refactoring.entity.Product;

public class ProductDAO extends AbstractDAO<Product> {

    public ProductDAO(Connection connection) {
        super(connection);
    }

    public void insert(Product product) {
        String sql = "INSERT INTO PRODUCT "
            + "(NAME, PRICE) VALUES (\"" + product.getName()
            + "\"," + product.getPrice() + ")";
        executeUpdate(sql);
    }

    public void deleteAll() {
        String sql = "DELETE FROM PRODUCT";
        executeUpdate(sql);
    }

    public List<Product> findAll() {
        return findByQuery("SELECT * FROM PRODUCT");
    }

    private List<Product> findByQuery(String sql) {
        List<Product> products = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                products.add(new Product(name, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
