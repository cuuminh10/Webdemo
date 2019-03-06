package springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springmvc.bean.Categories;

@Repository
public class CategoryDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	public List<Categories> getItem(){
		String sql="SELECT * FROM categories";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper(Categories.class));
	}

	public int addItem(Categories cat) {
		String sql = "insert into categories(id_parent,name) values(?,?)";
		return jdbcTemplate.update(sql,new Object[]{cat.getId_parent(),cat.getName()});
	}

	public Categories getCat(int id) {
		String sql="SELECT * FROM categories WHERE id=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {id},new BeanPropertyRowMapper<Categories>(Categories.class));
	}

	public int editItem(Categories categories) {
		String sql="update categories SET name=? WHERE id=?";
		return jdbcTemplate.update(sql,new Object[] {categories.getName(),categories.getId()});
	}

	public int delItem(Integer id) {
		String sql="delete from categories where id=?";
		return jdbcTemplate.update(sql,new Object[] {id});
	}
}
