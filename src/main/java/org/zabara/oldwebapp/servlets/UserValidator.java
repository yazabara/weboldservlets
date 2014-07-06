package org.zabara.oldwebapp.servlets;

import org.zabara.oldwebapp.domain.User;
import org.zabara.oldwebapp.services.user.UserPepoImpl;
import org.zabara.oldwebapp.services.user.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Yaroslav on 06.07.2014.
 */
public class UserValidator extends HttpServlet {

    private UserRepository userRepository = UserPepoImpl.getInstance();
    private String FOLDER_PREFIX = "/WEB-INF/pages";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher rd;
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = validateLogin(name, password);

        if (user == null) {
            rd = req.getRequestDispatcher(FOLDER_PREFIX + "/loginError.jsp");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            rd = req.getRequestDispatcher(FOLDER_PREFIX + "/loginSuccess.jsp");
        }

        rd.forward(req, res);
    }

    private User validateLogin(String name, String password) {
        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        User user = userRepository.getUser(name);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password.trim())) {
            return null;
        }
        return user;
    }

}
