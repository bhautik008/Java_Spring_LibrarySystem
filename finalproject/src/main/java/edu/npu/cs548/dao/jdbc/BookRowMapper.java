package edu.npu.cs548.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.cs548.domain.Book;

public class BookRowMapper implements RowMapper<Book> {

	public Book mapRow(ResultSet resultSet, int row) throws SQLException {
		int bookId;
		String title, content;
		int categoryId;
		String categoryName;
		Book book;
		
		bookId = resultSet.getInt("bookId");
		title = resultSet.getString("title");
		content = resultSet.getString("content");
		categoryId = resultSet.getInt("categoryId");
		categoryName = resultSet.getString("categoryName");
		
		book = new Book(bookId,title,content,categoryId);
		if(categoryName != null){
			book.setCategoryName(categoryName);
		}
		return book;
	}
	
}
