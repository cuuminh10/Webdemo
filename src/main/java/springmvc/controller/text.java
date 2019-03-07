package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springmvc.constant.Defines;

@Controller
public class text {
	@Autowired
	private Defines defines;
	@Autowired
 	private BCryptPasswordEncoder encoder;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("defines", defines);
	
	}

	@RequestMapping("test-pass")
	@ResponseBody
	public String index(ModelMap modelMap) {
		return encoder.encode("123456");
	}

	
}
