package org.zabara.oldwebapp.servlets;

import org.apache.log4j.Logger;
import org.zabara.oldwebapp.domain.Book;
import org.zabara.oldwebapp.services.book.BookRepository;
import org.zabara.oldwebapp.services.book.JDBCBookRepoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yaroslav_Zabara on 6/16/2014.
 */
public class AddBookServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(BookServlet.class.getName());
	private BookRepository bookRepository = JDBCBookRepoImpl.getInstance();

	@Override
	public void init() throws ServletException {
		super.init();
		logger.info(BookServlet.class.getName() + "was initialized");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		if ("POST".equals(req.getMethod())) {
			Book book = new Book();
			String name = req.getParameter("name");
			String author = req.getParameter("author");
			String desc = req.getParameter("desc");
			String date = req.getParameter("date");
			Date dateD = new Date();
			try {
				dateD = new SimpleDateFormat("dd.MM.yyyy").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			book.setAuthor(author);
			book.setName(name);
			book.setCreateDate(dateD);
			book.setDescription(desc);
			boolean bookaWasAdded = true;
			try {
				bookRepository.addBook(book);
			} catch (Exception e) {
				bookaWasAdded = false;
			}
			if (bookaWasAdded) {
				resp.sendRedirect("/bookListServlet/booklist");
				logger.info("view redirected to [/bookListServlet/booklist]");
			} else {
				req.setAttribute("error" , "book.add.error");
				forward(req, resp);
			}

		} else {
			forward(req, resp);
		}
	}

	public void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/pages" + req.getPathInfo() + ".jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		logger.info("view navigated to [" + path + "]");
	}
}