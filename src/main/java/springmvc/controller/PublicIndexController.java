package springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.bean.Products;
import springmvc.constant.Defines;
import springmvc.dao.ProductsDAO;

@Controller
@RequestMapping("public")
public class PublicIndexController {
	@Autowired
	private Defines defines;
	
	@Autowired
	private ProductsDAO ProductsDAO;
	
	@ModelAttribute
	public void addCommomObject(ModelMap map) {
		map.addAttribute("defines",defines);
	}
	@RequestMapping("")
	public String index(ModelMap map) {
		List<Products> Item = ProductsDAO.getItems();
		return "public.index";
	}
	
	
}
