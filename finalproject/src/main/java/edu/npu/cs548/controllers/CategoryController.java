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

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	BookService bookService;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@RequestMapping(value = "/categoryIndex", method = RequestMethod.GET)
	public ModelAndView categoryIndex() {
		ModelAndView modelView;
		List<Category> categories = categoryService.getAllCategory();
 		modelView = new ModelAndView("categoryIndex");
 		modelView.addObject("categories", categories);
		return modelView;
	}
	
	@RequestMapping(value = "/newCategoryDataForm", method = RequestMethod.GET)
	public ModelAndView newCategoryDataForm() {
		ModelAndView modelView;
 		modelView = new ModelAndView("categoryDataForm"); 		
 		modelView.addObject("category", new Category());
		return modelView;
	}
	
	@RequestMapping(value = "/processNewCategoryForm", method = RequestMethod.POST)
	public ModelAndView processNewCategoryForm(@ModelAttribute("category") @Valid Category category, BindingResult result, HttpSession session) {
		ModelAndView modelView;
		
		if (result.hasErrors()) {
			/*  Re-present the form with error messages */
			modelView = new ModelAndView("categoryDataForm");
			return modelView;
		}
		
		categoryService.addNewCategory(category);
 		modelView = new ModelAndView("categoryIndex");
 		List<Category> categories = categoryService.getAllCategory();
 		modelView.addObject("categories", categories);
		return modelView;
	}
}
