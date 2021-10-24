package ru.akirakozov.sd.refactoring.servlet.queryCommands;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum QueryCommandEnum {
    MAX() {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("<h1>Product with max price: </h1>");

                    while (rs.next()) {
                        String name = rs.getString("name");
                        int price = rs.getInt("price");
                        response.getWriter().println(name + "\t" + price + "</br>");
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    },
    MIN {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("<h1>Product with min price: </h1>");

                    while (rs.next()) {
                        String name = rs.getString("name");
                        int price = rs.getInt("price");
                        response.getWriter().println(name + "\t" + price + "</br>");
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    },
    SUM {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT SUM(price) FROM PRODUCT");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("Summary price: ");

                    if (rs.next()) {
                        response.getWriter().println(rs.getInt(1));
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    },
    COUNT {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM PRODUCT");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("Number of products: ");

                    if (rs.next()) {
                        response.getWriter().println(rs.getInt(1));
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    },
    DEFAULT {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response) {
            try {
                response.getWriter().println("Unknown command: " + request.getParameter("command"));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    };

    public abstract void runCommand(HttpServletRequest request, HttpServletResponse response);
}
