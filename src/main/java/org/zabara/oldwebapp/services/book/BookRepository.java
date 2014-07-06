package org.zabara.oldwebapp.services.book;

import org.zabara.oldwebapp.domain.Book;
import org.zabara.oldwebapp.domain.Group;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yaroslav on 14.06.2014.
 */
public interface BookRepository {

    Book getBook(int id) throws SQLException, ClassNotFoundException, BookException;

    List<Book> getBooks(String query) throws SQLException, ClassNotFoundException, BookException;

    List<Group> getGroups(Book book) throws BookException, SQLException, ClassNotFoundException;

    Group getGroup(int id) throws SQLException, ClassNotFoundException;

	void addBook(Book book) throws SQLException, ClassNotFoundException, BookException;

}
