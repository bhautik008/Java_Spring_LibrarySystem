package edu.npu.cs548.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs548.dao.BookDao;
import edu.npu.cs548.domain.Book;

@Service
//@Transactional
public class BookService implements BookServiceImpt{
	
	@Autowired
	@Qualifier("bookDaoJdbc")  // Use for jdbc connection
	//@Qualifier("bookDaoMock") // use for mock dao  
	BookDao bookDao;
	
	@Override
	public Book findBookById(int bookId){
		return bookDao.findBookById(bookId);
	}

	@Override
	public List<Book> getAllBook() {
		return bookDao.getAllBook();
	}

	@Override
	public List<Book> getBookByCategory(int categoryId) {
		return bookDao.getBookByCategory(categoryId);
	}

	@Override
	public Book addNewBook(Book book) {
		return bookDao.addNewBook(book);
	}

	@Override
	public Book updateBookById(int bookId, Book updateBook) {
		return bookDao.updateBookById(bookId, updateBook);
	}

	@Override
	public Book removeBookById(int bookId) {
		return bookDao.removeBookById(bookId);
	}

}
