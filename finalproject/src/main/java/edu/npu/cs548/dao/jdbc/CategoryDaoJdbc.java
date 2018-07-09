package edu.npu.cs548.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.cs548.dao.CategoryDao;
import edu.npu.cs548.domain.Category;

@Repository("categoryDaoJdbc")
@Transactional
public class CategoryDaoJdbc implements CategoryDao{

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private CategoryRowMapper categoryRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		categoryRowMapper = new CategoryRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("category")
		                 .usingGeneratedKeyColumns("categoryId")
		                 .usingColumns("categoryName");
	}
	
	@Override
	public Category getCategoryById(int categoryId) {
		String sql = "SELECT * FROM category WHERE categoryId = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", categoryId);
		List<Category> matchingCategories = dbTemplate.query(sql, params, categoryRowMapper);

		if (matchingCategories.size() == 0) {
			return null;
		}
		
		return matchingCategories.get(0);
	}

	@Override
	public List<Category> getAllCategory() {
		String sql = "SELECT * FROM category";
		List<Category> categoryList = jdbcTemplate.query(sql, categoryRowMapper);
		return categoryList;
	}
	
	private MapSqlParameterSource getCategoryParamMap(Category category) {
		MapSqlParameterSource params = new MapSqlParameterSource("categoryId", category.getCategoryId());
		params.addValue("categoryName", category.getCategoryName());
		return params;
	}

	@Override
	public Category addNewCategory(Category category) {
		MapSqlParameterSource params = getCategoryParamMap(category);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	    category.setCategoryId(newId.intValue());
	    return category;
	}

	@Override
	public Category updateCategoryById(int categoryId, Category updateCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category removeCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
