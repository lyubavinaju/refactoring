package ru.akirakozov.sd.refactoring.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.database.ProductConnectionSource;

public abstract class BaseServletTest {
    @Mock
    protected HttpServletRequest request;
    @Mock
    protected HttpServletResponse response;

    protected StringWriter stringWriter;
    protected PrintWriter printWriter;
    protected ProductDAO productDAO;

    private AutoCloseable closeable;

    @BeforeMethod
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        productDAO = new ProductDAO();
        productDAO.deleteAll();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        productDAO.deleteAll();
        printWriter.close();
        stringWriter.close();

        closeable.close();
    }
}
