package ru.akirakozov.sd.refactoring.servlet.queryCommands;

import java.io.IOException;
import java.io.UncheckedIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.entity.Product;

public enum QueryCommandEnum {
    MAX() {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response,
                               ProductDAO productDAO) {
            try {
                Product product = productDAO.findMaxByPrice();
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Product with max price: </h1>");
                if (product != null) {
                    response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
                }
                response.getWriter().println("</body></html>");
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    },
    MIN {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response,
                               ProductDAO productDAO) {
            try {
                Product product = productDAO.findMinByPrice();
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Product with min price: </h1>");
                if (product != null) {
                    response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
                }
                response.getWriter().println("</body></html>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    },
    SUM {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response,
                               ProductDAO productDAO) {
            try {
                Integer sum = productDAO.sum();
                response.getWriter().println("<html><body>");
                response.getWriter().println("Summary price: ");
                if (sum != null) {
                    response.getWriter().println(sum);
                }
                response.getWriter().println("</body></html>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    },
    COUNT {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response,
                               ProductDAO productDAO) {
            try {
                Integer count = productDAO.count();
                response.getWriter().println("<html><body>");
                response.getWriter().println("Number of products: ");
                if (count != null) {
                    response.getWriter().println(count);
                }
                response.getWriter().println("</body></html>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    },
    DEFAULT {
        @Override
        public void runCommand(HttpServletRequest request, HttpServletResponse response,
                               ProductDAO productDAO) {
            try {
                response.getWriter().println("Unknown command: " + request.getParameter("command"));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    };

    public abstract void runCommand(HttpServletRequest request, HttpServletResponse response,
                                    ProductDAO productDAO);
}
