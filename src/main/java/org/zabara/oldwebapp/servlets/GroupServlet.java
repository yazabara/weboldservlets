package org.zabara.oldwebapp.servlets;

import org.apache.log4j.Logger;
import org.zabara.oldwebapp.domain.Book;
import org.zabara.oldwebapp.domain.Group;
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
public class GroupServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(GroupServlet.class.getName());
    private BookRepository bookRepository = JDBCBookRepoImpl.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info(GroupServlet.class.getName() + "was initialized");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Group group = null;
        try {
            group = bookRepository.getGroup(Integer.parseInt(req.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("group", group);
        String path = "/WEB-INF/pages" + req.getPathInfo() + ".jsp";
        req.getRequestDispatcher(path).forward(req, resp);
        logger.info("view navigated to [" + path + "]");
    }
}
