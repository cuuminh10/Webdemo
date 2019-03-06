package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.constant.Defines;

@Controller
@RequestMapping(value="admin")
public class AdmnIndexController {
	@Autowired
	private Defines defines;
	@ModelAttribute
	public void addCommon(ModelMap m) {
		m.addAttribute("defines", defines);
	}

	
	@RequestMapping(value="")
	public String index() {
		return "admin.index";
	}
}
