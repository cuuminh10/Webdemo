package springmvc.bean;

public class Warehouse {
	private int id_prod;
	private int quanlity;
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public Warehouse(int id_prod, int quanlity) {
		super();
		this.id_prod = id_prod;
		this.quanlity = quanlity;
	}
	public Warehouse() {
		super();
	}
	
}
