package org.zabara.oldwebapp.services;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.zabara.oldwebapp.domain.Book;
import org.zabara.oldwebapp.domain.Group;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav on 15.06.2014.
 */
public class JDBCBookRepoImpl implements BookRepository {

	//default values
	private static String url = "jdbc:h2:~/test";
	private static String name = "sa";
	private static String password = "";
	private static String driverName = "org.h2.Driver";

	private static JDBCBookRepoImpl instance = null;
	private static final Logger logger = Logger.getLogger(JDBCBookRepoImpl.class.getName());

	static {
		instance = new JDBCBookRepoImpl();
	}

	private JDBCBookRepoImpl() {
		logger.info(JDBCBookRepoImpl.class.getName() + " was initialized");
	}

	public static JDBCBookRepoImpl getInstance() {
		if (instance == null) {
			instance = new JDBCBookRepoImpl();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driverName);
		return DriverManager.getConnection(url, name, password);
	}

	@Override
	public Book getBook(int id) throws SQLException, ClassNotFoundException, BookException {
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM BOOK where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			Book book = new Book();
			if (result.next()) {
				book.setId(result.getInt("id"));
				book.setName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setCreateDate(result.getDate("create_date"));
				book.setDescription(result.getString("description"));
			} else {
				throw new BookException("Book not found");
			}
			return book;
		} finally {
			connection.close();
		}

	}

	@Override
	public List<Book> getBooks(String query) throws SQLException, ClassNotFoundException, BookException {
		List<Book> books = new ArrayList<Book>();
		Connection connection = getConnection();
		if (query == null) {
			query = "";
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT\n" +
							"  *\n" +
							"FROM BOOK\n" +
							"WHERE BOOK.AUTHOR LIKE ? OR BOOK.DESCRIPTION LIKE ? OR BOOK.NAME LIKE ?;");
			preparedStatement.setString(1, "%" + query + "%");
			preparedStatement.setString(2, "%" + query + "%");
			preparedStatement.setString(3, "%" + query + "%");

			ResultSet result = preparedStatement.executeQuery();


			while (result.next()) {
				Book book = new Book();
				book.setId(result.getInt("id"));
				book.setName(result.getString("name"));
				book.setAuthor(result.getString("author"));
				book.setCreateDate(result.getDate("create_date"));
				book.setDescription(result.getString("description"));
				books.add(book);
			}

			return books;
		} finally {
			connection.close();
		}
	}

	@Override
	public List<Group> getGroups(Book book) throws BookException, SQLException, ClassNotFoundException {
		ResultSet result = null;
		Connection connection = getConnection();
		if (book == null) {
			result = getAllGroupsResultSet(connection);
		} else {
			result = getGroupsByBookResultSet(connection, book);
		}
		List<Group> resultList = new ArrayList<Group>();

		try {
			Group group = new Group();
			List<Book> books = new ArrayList<Book>();
			int groupId = -1;
			while (result.next()) {
				if (groupId == -1) {
					group.setId(result.getInt("group_id"));
					group.setName(result.getString("group_name"));
					group.setDescription(result.getString("group_description"));
					groupId = group.getId();
				} else if (result.getInt("group_id") != groupId) {
					resultList.add(new Group(group.getId(), group.getName(), group.getDescription(), Lists.newArrayList(books)));
					groupId = -1;
					books.clear();
				}
				//иначе просто добавляем книгу в список к текущей группе
				Book newBook = new Book();
				newBook.setId(result.getInt("book_id"));
				newBook.setName(result.getString("name"));
				newBook.setAuthor(result.getString("author"));
				newBook.setCreateDate(result.getDate("create_date"));
				newBook.setDescription(result.getString("description"));
				books.add(newBook);
			}
			group.setBooks(Lists.newArrayList(books));

			if (group.getId() == 0 && group.getName() == null && group.getDescription() == null && group.getBooks().size() == 0) {
				//bad group
			} else {
				resultList.add(group);
			}

			return resultList;
		} finally {
			connection.close();
		}
	}

	@Override
	public Group getGroup(int id) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
					"  tbl2.GROUP_ID,\n" +
					"  tbl2.NAME as GROUP_NAME,\n" +
					"  tbl2.DESCRIPTION as GROUP_DESCRIPTION,\n" +
					"  tbl2.BOOK_ID,\n" +
					"  BOOK.NAME,\n" +
					"  BOOK.AUTHOR,\n" +
					"  BOOK.DESCRIPTION,\n" +
					"  BOOK.CREATE_DATE\n" +
					"FROM\n" +
					"  BOOK\n" +
					"  INNER JOIN\n" +
					"  (SELECT\n" +
					"     NAME,\n" +
					"     DESCRIPTION,\n" +
					"     GROUP_ID,\n" +
					"     BOOK_ID\n" +

					"   FROM\n" +
					"     BOOK_GROUP\n" +
					"     INNER JOIN\n" +
					"     GROUP_BOOK_CONNECTION\n" +
					"       ON BOOK_GROUP.ID = GROUP_BOOK_CONNECTION.GROUP_ID) AS tbl2\n" +
					"    ON book.ID = tbl2.BOOK_ID WHERE GROUP_ID = ? ORDER BY group_id;");
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			Group group = new Group();
			List<Book> books = new ArrayList<Book>();
			int groupId = -1;
			while (result.next()) {
				if (groupId == -1) {
					group.setId(result.getInt("group_id"));
					group.setName(result.getString("group_name"));
					group.setDescription(result.getString("group_description"));
					groupId = group.getId();
				} else if (result.getInt("group_id") != groupId) {
					//не должно быть такого, но мало ли.
					break;
				}
				//иначе просто добавляем книгу в список к текущей группе
				Book newBook = new Book();
				newBook.setId(result.getInt("book_id"));
				newBook.setName(result.getString("name"));
				newBook.setAuthor(result.getString("author"));
				newBook.setCreateDate(result.getDate("create_date"));
				newBook.setDescription(result.getString("description"));
				books.add(newBook);
			}
			group.setBooks(Lists.newArrayList(books));

			return group;
		} finally {
			connection.close();
		}
	}


	private ResultSet getAllGroupsResultSet(Connection connection) throws SQLException, ClassNotFoundException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
				"  tbl2.GROUP_ID,\n" +
				"  tbl2.NAME as GROUP_NAME,\n" +
				"  tbl2.DESCRIPTION as GROUP_DESCRIPTION,\n" +
				"  tbl2.BOOK_ID,\n" +
				"  BOOK.NAME,\n" +
				"  BOOK.AUTHOR,\n" +
				"  BOOK.DESCRIPTION,\n" +
				"  BOOK.CREATE_DATE\n" +
				"FROM\n" +
				"  BOOK\n" +
				"  INNER JOIN\n" +
				"  (SELECT\n" +
				"     NAME,\n" +
				"     DESCRIPTION,\n" +
				"     GROUP_ID,\n" +
				"     BOOK_ID\n" +
				"   FROM\n" +
				"     BOOK_GROUP\n" +
				"     INNER JOIN\n" +
				"     GROUP_BOOK_CONNECTION\n" +
				"       ON BOOK_GROUP.ID = GROUP_BOOK_CONNECTION.GROUP_ID) AS tbl2\n" +
				"    ON book.ID = tbl2.BOOK_ID ORDER BY group_id;");


		return preparedStatement.executeQuery();
	}

	private ResultSet getGroupsByBookResultSet(Connection connection, Book book) throws SQLException, ClassNotFoundException {
		PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT\n" +
						"  tbl2.GROUP_ID,\n" +
						"  tbl2.NAME as GROUP_NAME,\n" +
						"  tbl2.DESCRIPTION as GROUP_DESCRIPTION,\n" +
						"  tbl2.BOOK_ID,\n" +
						"  BOOK.NAME,\n" +
						"  BOOK.DESCRIPTION,\n" +
						"  BOOK.AUTHOR,\n" +
						"  BOOK.CREATE_DATE\n" +
						"FROM\n" +
						"  BOOK\n" +
						"  INNER JOIN\n" +
						"\n" +
						"  (SELECT\n" +
						"     GROUP_ID,\n" +
						"     NAME,\n" +
						"     DESCRIPTION,\n" +
						"     BOOK_ID\n" +
						"   FROM\n" +
						"     BOOK_GROUP\n" +
						"     INNER JOIN\n" +
						"     (SELECT\n" +
						"        *\n" +
						"      FROM\n" +
						"        GROUP_BOOK_CONNECTION\n" +
						"      WHERE GROUP_BOOK_CONNECTION.GROUP_ID IN\n" +
						"            (SELECT\n" +
						"               GROUP_ID\n" +
						"             FROM\n" +
						"               GROUP_BOOK_CONNECTION\n" +
						"             WHERE GROUP_BOOK_CONNECTION.BOOK_ID = ?)) AS tbl1\n" +
						"       ON BOOK_GROUP.ID = tbl1.GROUP_ID) AS tbl2\n" +
						"    ON book.ID = tbl2.BOOK_ID ORDER BY group_id;");
		preparedStatement.setInt(1, book.getId());
		return preparedStatement.executeQuery();
	}

	@Override
	public void addBook(Book book) throws SQLException, ClassNotFoundException, BookException {
		Connection connection = getConnection();
		if (!book.isCorrect()) {
			throw new BookException("Book is incorrect");
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO BOOK (NAME, AUTHOR, DESCRIPTION, CREATE_DATE) VALUES (?,?,?,?)");
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getDescription());
			String newstring = new SimpleDateFormat("yyyy-MM-dd").format(book.getCreateDate());
			preparedStatement.setString(4, newstring);
			preparedStatement.executeUpdate();
		} finally {
			connection.close();
		}
	}
}