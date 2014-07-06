package org.zabara.oldwebapp.services.book;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.zabara.oldwebapp.domain.Book;
import org.zabara.oldwebapp.domain.Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 14.06.2014.
 *
 * Тестовый репозиторий для отладки UI
 */
public class BookRepoImpl implements BookRepository {

    private static BookRepoImpl instance = null;
    private static List<Book> books;
    private static List<Group> groups;

    private static final Logger logger = Logger.getLogger(BookRepoImpl.class.getName());

    private BookRepoImpl() {
        books = new ArrayList<Book>();
        groups = new ArrayList<Group>();
    }

    static {
        instance = getInstance();
        test();
    }

    public static BookRepoImpl getInstance() {
        if (instance == null) {
            instance = new BookRepoImpl();
        }
        return instance;
    }

	@Override
	public void addBook(Book book) {

	}

	private static void test() {
        books.add(new Book(1, "name", "descr", new Date(), "author"));
        books.add(new Book(2, "name1", "descr", new Date(), "author"));
        books.add(new Book(3, "name2", "descr", new Date(), "author"));
        books.add(new Book(4, "name3", "descr", new Date(), "author"));

        groups.add(new Group(1, "first", "qwe", books));
        groups.add(new Group(2, "second", "ewq", books));
        groups.add(new Group(3, "last", "ewq", books));
    }

    @Override
    public Book getBook(final int id) {
        Predicate<Book> filter = new Predicate<Book>() {
            @Override
            public boolean apply(Book book) {
                return book.getId() == id;
            }
        };

        return Iterables.find(books, filter);
    }

    @Override
    public List<Book> getBooks(String query) {
        return books;
    }

    @Override
    public List<Group> getGroups(final Book book) {
        if (book == null) {
            return groups;
        }
        Predicate<Group> filter = new Predicate<Group>() {
            @Override
            public boolean apply(Group group) {
                for (Book book1 : group.getBooks()) {
                    if (book.getId() == book1.getId()) {
                        return true;
                    }
                }
                return false;
            }
        };

        return Lists.newArrayList(Iterables.filter(groups, filter));
    }

    @Override
    public Group getGroup(final int id) {
        Predicate<Group> filter = new Predicate<Group>() {
            @Override
            public boolean apply(Group group) {
                return group.getId() == id;
            }
        };

        return Iterables.find(groups, filter);
    }
}
