package springmvc.bean;

public class Categories {
	private int id;
	private int id_parent;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_parent() {
		return id_parent;
	}
	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	public Categories(int id, int id_parent, String name) {
		super();
		this.id = id;
		this.id_parent = id_parent;
		this.name = name;
	}
	public Categories() {
		
	}
	
}
