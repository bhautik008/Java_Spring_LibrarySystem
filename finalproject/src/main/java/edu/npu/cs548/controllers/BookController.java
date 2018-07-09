package edu.npu.cs548.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.npu.cs548.domain.*;
import edu.npu.cs548.services.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(value = "/bookIndex", method = RequestMethod.GET)
	public ModelAndView bookIndex() {
		ModelAndView modelView;
		List<Book> books = bookService.getAllBook();
 		modelView = new ModelAndView("bookIndex");
 		modelView.addObject("books", books);
		return modelView;
	}
	
	@RequestMapping(value = "/bookCategoryIndex", method = RequestMethod.GET)
	public ModelAndView bookCategoryIndex(@RequestParam("id") int categoryId) {
		ModelAndView modelView;
		List<Book> books = bookService.getBookByCategory(categoryId);
 		modelView = new ModelAndView("bookIndex");
 		modelView.addObject("books", books);
		return modelView;
	}
	
	@RequestMapping(value = "/newBookDataForm", method = RequestMethod.GET)
	public ModelAndView newBookDataForm() {
		ModelAndView modelView;
		List<Category> categories = categoryService.getAllCategory();
 		modelView = new ModelAndView("bookDataForm");
 		modelView.addObject("categories", categories);
 		modelView.addObject("book", new Book());
		return modelView;
	}
	
	@RequestMapping(value = "/processNewBookForm", method = RequestMethod.POST)
	public ModelAndView processNewBookForm(@ModelAttribute("book") @Valid Book book, BindingResult result, HttpSession session) {
		ModelAndView modelView;
		
		if (result.hasErrors()) {
			/*  Re-present the form with error messages */
			modelView = new ModelAndView("bookDataForm");
			return modelView;
		}
		
		bookService.addNewBook(book);
		List<Book> books = bookService.getAllBook();
 		modelView = new ModelAndView("bookIndex");
 		modelView.addObject("books", books);
		return modelView;
	}
}