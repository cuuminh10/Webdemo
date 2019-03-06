package springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springmvc.bean.Products;

@Repository
public class ProductsDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Products> getItems(){
		String sql="SELECT p.*,c.name as cname FROM products as p INNER JOIN categories as c ON p.id_cat = c.id";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Products>(Products.class));
	}

	public int addItem(Products prod) {
		String sql="INSERT INTO products(name,id_cat,price,sale,date_create,picture,status,description,id_user) VALUE(?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,new Object[] {prod.getName(),prod.getId_cat(),prod.getPrice(),prod.getSale(),prod.getDate_create(),prod.getPicture(),prod.getStatus(),prod.getDescription(),1});
		
	}
	
	public Products getItem(int id) {
		String sql ="SELECT * FROM products WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id},new BeanPropertyRowMapper<Products>(Products.class));
	}
	
	public int updateItem(Products prod) {
		String sql="UPDATE products SET name=?,id_cat=?,price=?,sale=?,date_create=?,picture=?,status=?,description=?,id_user=? Where id=?";
		return jdbcTemplate.update(sql,new Object[] {prod.getName(),prod.getId_cat(),prod.getPrice(),prod.getSale(),prod.getDate_create(),prod.getPicture(),prod.getStatus(),prod.getDescription(),1,prod.getId()});
		
	}
}
