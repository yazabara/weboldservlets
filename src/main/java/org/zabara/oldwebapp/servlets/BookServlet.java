package org.zabara.oldwebapp.servlets;

import org.apache.log4j.Logger;
import org.zabara.oldwebapp.domain.Book;
import org.zabara.oldwebapp.services.BookException;
import org.zabara.oldwebapp.services.BookRepoImpl;
import org.zabara.oldwebapp.services.BookRepository;
import org.zabara.oldwebapp.services.JDBCBookRepoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Yaroslav on 14.06.2014.
 */
public class BookServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(BookServlet.class.getName());
    private BookRepository bookRepository = JDBCBookRepoImpl.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info(BookServlet.class.getName() + "was initialized");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = null;
        try {
            book = bookRepository.getBook(Integer.parseInt(req.getParameter("id")));
        } catch (BookException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("book", book);
        try {
            req.setAttribute("groups", bookRepository.getGroups(book));
        } catch (BookException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String path = "/WEB-INF/pages" + req.getPathInfo() + ".jsp";
        req.getRequestDispatcher(path).forward(req, resp);
        logger.info("view navigated to [" + path + "]");
//		throw new IOException("куй");
    }
}
