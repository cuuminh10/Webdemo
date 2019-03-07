package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.constant.Defines;
@Controller
@RequestMapping("auth")
public class AdminAuthController {
	@Autowired
	private Defines defines;
	
	@ModelAttribute
	public void addCommomObjetc(ModelMap map) {
		map.addAttribute("defines",defines);
	}
	@RequestMapping("login")
	public String login() {
		
		return "auth.login"; 
	}
	
	@RequestMapping("403")
	public String error() {
		
		return "auth.403"; 
	}
}
