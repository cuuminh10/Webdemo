package springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springmvc.bean.Warehouse;

@Repository
public class WarehouseDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int delItem(int id){
		String sql="delete from warehouse where id_prod = ?";
		return jdbcTemplate.update(sql,new Object[] {id});
	}
	
	public Warehouse getitems(int id) {
		String sql="select * from warehouse where id_prod = ?";
	    return jdbcTemplate.queryForObject(sql,new Object[] {id},new BeanPropertyRowMapper<Warehouse>(Warehouse.class));
	}
	
	public Warehouse additems(int id,int qty) {
		String sql="INSERT INTO warehouse(id_prod,quanlity) VALUES(?,?)";
	    return jdbcTemplate.queryForObject(sql,new Object[] {id,qty},new BeanPropertyRowMapper<Warehouse>(Warehouse.class));
	}
	
	public int update(int id,int qty) {
		String sql="UPDATE warehouse SET quanlity=? where id_prod=?";
	    return jdbcTemplate.update(sql,new Object[] {qty,id});
	}
}
