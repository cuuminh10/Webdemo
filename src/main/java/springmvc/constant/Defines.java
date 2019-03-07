package springmvc.constant;

public class Defines {
	// định nghĩa các thông số cho ứng dụng web
	// @TODO
	public String urlPublic;
	public String urlAdmin;
	public String urlLogin;
	public String superAdmin;
	public static final String DIR_UPLOAD="files";
	public static final String[] nameAccess= {"jpg","png","gif"};
	public final String SUCCESS ="Xử lý thành công";
	public final String ERROR = "Có lỗi trong quá trình";
	
	public String getUrlLogin() {
		return urlLogin;
	}
	public void setUrlLogin(String urlLogin) {
		this.urlLogin = urlLogin;
	}
	public String getUrlPublic() {
		return urlPublic;
	}
	public void setUrlPublic(String urlPublic) {
		this.urlPublic = urlPublic;
	}
	public String getUrlAdmin() {
		return urlAdmin;
	}
	public void setUrlAdmin(String urlAdmin) {
		this.urlAdmin = urlAdmin;
	}
	
	
	public Defines(String urlPublic, String urlAdmin, String urlLogin, String superAdmin) {
		super();
		this.urlPublic = urlPublic;
		this.urlAdmin = urlAdmin;
		this.urlLogin = urlLogin;
		this.superAdmin = superAdmin;
	}
	public String getSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}
	public Defines() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
