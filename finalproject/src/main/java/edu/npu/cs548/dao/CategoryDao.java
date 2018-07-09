package edu.npu.cs548.dao;

import java.util.List;
import edu.npu.cs548.domain.Category;

public interface CategoryDao {
	public Category getCategoryById(int categoryId);
	public List<Category> getAllCategory(); 
	public Category addNewCategory(Category category);
	public Category updateCategoryById(int categoryId, Category updateCategory);
	public Category removeCategoryById(int categoryId);
}
