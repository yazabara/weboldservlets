package org.zabara.oldwebapp.filters;

import org.zabara.oldwebapp.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Yaroslav on 06.07.2014.
 */
public class AuthFilter implements Filter {

    private String LOGIN_ACTION_URI = "";
    private String FOLDER_PREFIX = "/WEB-INF/pages";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGIN_ACTION_URI = filterConfig.getInitParameter("loginActionURI");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null && !LOGIN_ACTION_URI.equals(req.getRequestURI())){
            RequestDispatcher rd = req.getRequestDispatcher(FOLDER_PREFIX + "/login.jsp");
            rd.forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
