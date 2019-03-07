package springmvc.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.bean.User;
import springmvc.constant.Defines;
import springmvc.dao.UserDAO;

@Controller
@RequestMapping(value="admin")
public class AdmnIndexController {
	@Autowired
	private Defines defines;
	
	@Autowired
	private UserDAO userDAO;
	
	@ModelAttribute
	public void addCommon(ModelMap m) {
		m.addAttribute("defines", defines);
	}

	
	@RequestMapping(value="")
	public String index(HttpSession session,Principal principal) {
		User user = (User) session.getAttribute("userInfo");
		if(session.getAttribute("userInfo") == null) {
			int id = userDAO.getItem(principal.getName()).getId();
			int enable = userDAO.getItem(principal.getName()).getEnable();
			int role_id = userDAO.getItem(principal.getName()).getRole_id();
			String username = userDAO.getItem(principal.getName()).getUsername();
			String fullname = userDAO.getItem(principal.getName()).getFullname();
			String password = userDAO.getItem(principal.getName()).getPassword();
			String email = userDAO.getItem(principal.getName()).getEmail();
			String phone = userDAO.getItem(principal.getName()).getPhone();
			String address = userDAO.getItem(principal.getName()).getAddress();
			user = new User(id, enable, role_id, username, fullname, password, email, phone, address);
			session.setAttribute("userInfo", user);	
		}
		return "admin.index";
	}
}
