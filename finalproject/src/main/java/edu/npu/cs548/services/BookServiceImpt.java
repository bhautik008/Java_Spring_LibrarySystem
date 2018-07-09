package edu.npu.cs548.services;

import java.util.List;
import edu.npu.cs548.domain.Book;

public interface BookServiceImpt {
	public Book findBookById(int bookId);
	public List<Book> getAllBook();
	public List<Book> getBookByCategory(int categoryId);
	public Book addNewBook(Book book);
	public Book updateBookById(int bookId, Book updateBook);
	public Book removeBookById(int bookId);
}
