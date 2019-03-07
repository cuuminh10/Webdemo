package springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springmvc.bean.User;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getItem(String username) {
		String sql="SELECT * FROM user where username=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {username},new BeanPropertyRowMapper<User>(User.class));
	}
}
