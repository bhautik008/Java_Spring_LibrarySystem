package edu.npu.cs548.restclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import edu.npu.cs548.domain.*;

public class BookServicesClient {
	
	static private Logger logger = Logger.getLogger(BookServicesClient.class);
	private static String BOOK_SERVICES_URL = "http://localhost:8088/cs548/webservices/book/";
	private static String BOOK_LOOKUP_URL = BOOK_SERVICES_URL + "{id}";
	private static Client client=null;
	
	public static void main(String[] args){
		testLookupBook();
		testPost();
	}
	
	private static Client getClient() {
		if (client == null) {
			client = ClientBuilder.newClient();
		}
		
		return client;
	}
	
	public static Book testLookupBook() {
		int idToLookup = 1;  /* Just some hardcoded test data -- a student with this id must be in the database */
		int responseCode;
		Book book=null;
		
		Client client = getClient();
		
		//Targeting the RESTful Webservice we want to invoke by capturing it in WebTarget instance.
		WebTarget target = client.target(BOOK_LOOKUP_URL);
		target = target.resolveTemplate("id", idToLookup);
		
		// Now that we have the URI in the target, build the request i.e a GET request to the RESTful Webservice 
		Builder request = target.request(MediaType.APPLICATION_XML_TYPE);
		Response response = request.get();
		
		responseCode = response.getStatus();
		logger.info("The response code is: " + responseCode);
		if (responseCode == 200) {
			book = response.readEntity(Book.class);
			logger.info(book);
		}
		
		return book;
	}
	
	
	/* Using a POST Http Command, we'll add a completely new student */
	public static void testPost() {
		int responseCode;
		Book newStudent;
		Client client = getClient();
		
		newStudent = createNewBook();
		
		WebTarget target = client.target(BOOK_SERVICES_URL);
		
		Builder request = target.request();
		request.accept(MediaType.APPLICATION_XML_TYPE);
		Response response = request.post(Entity.xml(newStudent));
		
		responseCode = response.getStatus();
		logger.info("The response code from Post operation is: " + responseCode);
		
		if (responseCode == 200) {
			Book createdStudent = response.readEntity(Book.class);
			logger.debug(createdStudent);
		}
	}
	
	public static Book createNewBook() {
		Book book;
		
		book = new Book();
		book.setTitle("Testing REST");
		book.setContent("Testing REST");
		book.setCategoryId(3);
		return book;
	}
	
}
