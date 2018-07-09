package edu.npu.cs548.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.cs548.domain.Category;


public class CategoryRowMapper implements RowMapper<Category>{

	@Override
	public Category mapRow(ResultSet resultSet, int row) throws SQLException {
		int categoryId;
		String categoryName;
		Category category;
		
		categoryId = resultSet.getInt("categoryId");
		categoryName = resultSet.getString("categoryName");
		
		category = new Category(categoryId, categoryName);
		
		return category;
	}
}
