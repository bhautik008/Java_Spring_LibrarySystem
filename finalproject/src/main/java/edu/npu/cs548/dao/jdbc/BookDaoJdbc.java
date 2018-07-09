package edu.npu.cs548.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs548.dao.BookDao;
import edu.npu.cs548.domain.Book;

@Repository("bookDaoJdbc")
@Transactional
public class BookDaoJdbc implements BookDao {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private BookRowMapper bookRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		bookRowMapper = new BookRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("book")
		                 .usingGeneratedKeyColumns("bookId")
		                 .usingColumns("title", "content", "categoryId");
	}
	
	@Override
	public Book findBookById(int bookId) {
		String sql = "SELECT * FROM book WHERE bookId = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", bookId);
		List<Book> matchingBooks = dbTemplate.query(sql, params, bookRowMapper);

		if (matchingBooks.size() == 0) {
			return null;
		}
		
		return matchingBooks.get(0);
	}

	@Override
	public List<Book> getAllBook() {
		String sql = "SELECT book.*,category.categoryName FROM book join category on category.categoryId=book.categoryId";
		List<Book> bookList = jdbcTemplate.query(sql, bookRowMapper);
		return bookList;
	}

	@Override
	public List<Book> getBookByCategory(int categoryId) {
		String sql = "SELECT book.*,category.categoryName FROM book join category on category.categoryId=book.categoryId WHERE book.categoryId = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", categoryId);
		List<Book> matchingBooks = dbTemplate.query(sql, params, bookRowMapper);

		if (matchingBooks.size() == 0) {
			return null;
		}
		
		return matchingBooks;
	}
	
	private MapSqlParameterSource getBookParamMap(Book book) {
		MapSqlParameterSource params = new MapSqlParameterSource("bookId", book.getBookId());
		params.addValue("title", book.getTitle());
		params.addValue("content", book.getContent());
		params.addValue("categoryId", book.getCategoryId());
		return params;
	}

	@Override
	public Book addNewBook(Book book) {
		MapSqlParameterSource params = getBookParamMap(book);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	    book.setBookId(newId.intValue());
	    return book;
	}

	@Override
	public Book updateBookById(int bookId, Book updateBook) {
		String sql = "UPDATE book SET title = :newTitle, content = :newContent, categoryId = :newCategoryID WHERE bookId = :bookId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("newTitle", updateBook.getTitle());
		params.addValue("newContent", updateBook.getContent());
		params.addValue("newCategoryID", updateBook.getCategoryId());
		params.addValue("bookId", bookId);
		jdbcTemplate.update(sql, params);
		return updateBook;
	}

	@Override
	public Book removeBookById(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
