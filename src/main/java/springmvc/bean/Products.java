package springmvc.bean;

import java.sql.Timestamp;

public class Products {
	private Integer id;
	private String name;
	private Integer id_cat;
	private Double price;
	private Double sale;
	private Timestamp date_create;
	private String picture;
	private int status;
	private String description;
	private int id_user;
	private String cname;
	
	
	

	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Integer getId_cat() {
		return id_cat;
	}




	public void setId_cat(Integer id_cat) {
		this.id_cat = id_cat;
	}




	public Double getPrice() {
		return price;
	}




	public void setPrice(Double price) {
		this.price = price;
	}




	public Double getSale() {
		return sale;
	}




	public void setSale(Double sale) {
		this.sale = sale;
	}




	public Timestamp getDate_create() {
		return date_create;
	}




	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}




	public String getPicture() {
		return picture;
	}




	public void setPicture(String picture) {
		this.picture = picture;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public int getId_user() {
		return id_user;
	}




	public void setId_user(int id_user) {
		this.id_user = id_user;
	}




	public String getCname() {
		return cname;
	}




	public void setCname(String cname) {
		this.cname = cname;
	}
	
	 



	public Products(Integer id, String name, Integer id_cat, Double price, Double sale, Timestamp date_create,
			String picture, int status, String description, int id_user, String cname) {
		super();
		this.id = id;
		this.name = name;
		this.id_cat = id_cat;
		this.price = price;
		this.sale = sale;
		this.date_create = date_create;
		this.picture = picture;
		this.status = status;
		this.description = description;
		this.id_user = id_user;
		this.cname = cname;
	}




	public Products() {
		super();
		
	}
	
}
