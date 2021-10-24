package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.html.HtmlPrinter;

public abstract class BaseServlet extends HttpServlet {
    protected final ProductDAO productDAO;
    protected final HtmlPrinter htmlPrinter;

    protected BaseServlet(ProductDAO productDAO, HtmlPrinter htmlPrinter) {
        this.productDAO = productDAO;
        this.htmlPrinter = htmlPrinter;
    }

}
