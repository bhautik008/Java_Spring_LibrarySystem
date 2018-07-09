package edu.npu.cs548.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs548.dao.CategoryDao;
import edu.npu.cs548.domain.Category;

@Service
//@Transactional
public class CategoryService implements CategoryServiceImpt{

	@Autowired
	@Qualifier("categoryDaoJdbc")  // Use for jdbc connection
	//@Qualifier("categoryDaoMock") // use for mock dao  
	CategoryDao categoryDao;
	
	@Override
	public Category getCategoryById(int categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.getAllCategory();
	}

	@Override
	public Category addNewCategory(Category category) {
		return categoryDao.addNewCategory(category);
	}

	@Override
	public Category updateCategoryById(int categoryId, Category updateCategory) {
		return categoryDao.updateCategoryById(categoryId, updateCategory);
	}

	@Override
	public Category removeCategoryById(int categoryId) {
		return categoryDao.removeCategoryById(categoryId);
	}

}
