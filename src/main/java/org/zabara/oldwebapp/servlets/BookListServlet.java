package org.zabara.oldwebapp.servlets;

import org.apache.log4j.Logger;
import org.zabara.oldwebapp.services.book.BookException;
import org.zabara.oldwebapp.services.book.BookRepository;
import org.zabara.oldwebapp.services.book.JDBCBookRepoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Yaroslav on 14.06.2014.
 */
public class BookListServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(BookListServlet.class.getName());
    private BookRepository bookRepository = JDBCBookRepoImpl.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info(BookListServlet.class.getName() + "was initialized");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
			req.setCharacterEncoding("UTF-8");
            req.setAttribute("bookList", bookRepository.getBooks(req.getParameter("question")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (BookException e) {
            e.printStackTrace();
        }
        String path = "/WEB-INF/pages" + req.getPathInfo() + ".jsp";
        req.getRequestDispatcher(path).forward(req, resp);
        logger.info("view navigated to [" + path + "]");
    }
}
