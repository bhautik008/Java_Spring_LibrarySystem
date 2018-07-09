package edu.npu.cs548.resthandlers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.npu.cs548.domain.*;
import edu.npu.cs548.exceptions.*;
import edu.npu.cs548.services.*;

@Path("/")
public class BookRestHandler {

	@Autowired
	private BookService bookService;
	private Logger logger = Logger.getLogger(BookRestHandler.class);
	
	/* Look up a book from the book id.   
	 * Matching Url:
	 * http://localhost:8088/cs548/webservices/book/{id}   Note -- replace {id} with a number (the id of the book)
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/book/{id}")
	@Produces("application/xml, application/json")
	public Book getBook(@PathParam("id") int id) {
		Book book = null;
		
		try {
			book = bookService.findBookById(id);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		
		if (book == null) {
			logger.debug("Failed Request to lookup student with id  : " + id);
			throw new UnknownResourceException("Student id: " + id + " is invalid");
		}
		
		return book;
	}
	
	/* Return a list of all the books
	 * Matching Url:
	 * http://localhost:8088/cs548/webservices/book
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/student")
	@Produces("application/xml")
	public List<Book> getBookList() {
		List<Book> bookList;
		
		bookList = bookService.getAllBook();
		return bookList;
	}
	
	
	/* Create a new Book
	 * URL:  http://localhost:8088/cs548/webservices/book
	 * After doing the post, use a get command to retrieve the student (and verify that the post was successful).
	 */
	@POST
	@Path("/student")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response addBook(Book newBook) {
		ResponseBuilder respBuilder;
		
		try { 
			bookService.addNewBook(newBook);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		
		logger.debug("Successfully created a new Book: " + newBook);
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(newBook);
		return respBuilder.build();
	}
}
