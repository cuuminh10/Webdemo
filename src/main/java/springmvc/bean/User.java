package springmvc.bean;

public class User {
	private int id;
	private int enable;
	private int role_id;
	private String username;
	private String fullname;
	private String password;
	private String email;
	private String phone;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(int id, int enable, int role_id, String username, String fullname, String password, String email,
			String phone, String address) {
		super();
		this.id = id;
		this.enable = enable;
		this.role_id = role_id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public User() {
		super();
	}
	
	
	
	
}
