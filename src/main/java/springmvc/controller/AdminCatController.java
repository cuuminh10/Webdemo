package springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springmvc.bean.Categories;
import springmvc.constant.Defines;
import springmvc.dao.CategoryDAO;

@Controller
@RequestMapping(value = "admin/cat")
public class AdminCatController {
	@Autowired
	private Defines defines;

	@Autowired
	private CategoryDAO categoryDAO;
	
	private List<String> listMenu = new ArrayList<String>();

	@ModelAttribute
	public void addCommon(ModelMap m, HttpServletRequest request) {
		HashMap<Integer, Categories> hmap = new HashMap<Integer, Categories>();
		if (listMenu.size() > 0) {
			listMenu = new ArrayList<String>();
		}
		List<Categories> listItem = null;
		listItem = categoryDAO.getItem();
		String urlHeader = request.getContextPath();
		for (Categories item : listItem) {

			Categories cat = new Categories(item.getId(), item.getId_parent(), item.getName());
			hmap.put(item.getId(), cat);

		}
		dequy(hmap, 0, "", urlHeader, "index");
		m.addAttribute("listmenu", listMenu);
		m.addAttribute("defines", defines);
		
	}

	@RequestMapping(value = "")
	public String cat(ModelMap map, HttpServletRequest request) {
		return "admin.cat.index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map, HttpServletRequest request) {
		HashMap<Integer, Categories> hmap = new HashMap<Integer, Categories>();
		List<Categories> listItem = null;
		listItem = categoryDAO.getItem();
		String urlHeader = request.getContextPath();
		for (Categories item : listItem) {
			Categories cat = new Categories(item.getId(), item.getId_parent(), item.getName());
			hmap.put(item.getId(), cat);
		}
		dequy(hmap, 0, "", urlHeader, "add");
		if (listItem.size() > 0) {
			map.addAttribute("listOption", listMenu);
			return "admin.cat.add";
		}
		System.out.println("menu null");
		return null;
	}
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable(value="id",required=false) Integer id) {
		if(id == null) {
			return "admin.cat.index";
		}
		map.addAttribute("item",categoryDAO.getCat(id));
		return "admin.cat.edit";
	}
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.POST)
	public String edit(@PathVariable(value="id",required=false) Integer id,@RequestParam("name") String name,RedirectAttributes rs) {
		Categories categories = new Categories(id,0,name);
		if(categoryDAO.editItem(categories) > 0) {
			rs.addFlashAttribute("msg",defines.SUCCESS);
		}else {
			rs.addFlashAttribute("err",defines.ERROR);
		}
		return "redirect:/admin/cat";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("categories") Categories categories, ModelMap map, RedirectAttributes rs) {
		System.out.println(categories.getId_parent());
		Categories cat = new Categories(0,categories.getId_parent(),categories.getName());
		if(categoryDAO.addItem(cat) > 0) {
			rs.addFlashAttribute("msg",defines.SUCCESS);
		}else {
			rs.addFlashAttribute("err",defines.ERROR);
		}
		return "redirect:/admin/cat";
		
	}
	
	@RequestMapping(value="del/{id}")
	public String del(RedirectAttributes rs,@PathVariable(value="id",required = false) Integer id) {
		if(id != null) {
			if (categoryDAO.delItem(id) > 0) {
				rs.addFlashAttribute("msg",defines.SUCCESS);
			}else {
				rs.addFlashAttribute("err",defines.ERROR);
			}
		}
		return "redirect:/admin/cat";
	}
	
	

	public void dequy(HashMap<Integer, Categories> hmap, int key, String c, String url, String info) {
		int i = 0;
		String table = "";

		for (Map.Entry<Integer, Categories> list : hmap.entrySet()) {
			if (list.getValue().getId_parent() == key && info.equalsIgnoreCase("index")) {

				table = "	<c:set var=delUrl value=${pageContext.request.contextPath}><c:set>\r\n"
						+ "  	<c:set var=editUrl value=${pageContext.request.contextPath}><c:set>\r\n"
						+ "   <tr class=\"odd gradeX\">\r\n" + "       <td>" + list.getKey() + "</td>\r\n"
						+ "       <td>" + c + list.getValue().getName() + "</td>\r\n"
						+ "       <td class=\"center text-center\" width=\"20%\">\r\n" + "        <a href=\"" + url
						+ "/admin/cat/edit/" + list.getKey()
						+ "\" title=\"\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-pencil \"></span> Sửa</a>\r\n"
						+ "        <a href=\"" + url + "/admin/cat/del/" + list.getKey()
						+ "\" title=\"\" class=\"btn btn-danger\" onclick=\"return confirm('Bạn có chắc mún xóa')\"><span class=\"glyphicon glyphicon-trash\"></span> Xóa</a>\r\n"
						+ "        </td>\r\n" + "    </tr>";
				listMenu.add(table);
				hmap.remove(hmap.get(list.getKey()));
				dequy(hmap, list.getValue().getId(), c + "---", url, info);
			} else {
				if (list.getValue().getId_parent() == key && info.equalsIgnoreCase("add")) {

					table = "<option value=" + list.getKey() + ">" + c + list.getValue().getName() + "</option>";
					listMenu.add(table);
					hmap.remove(hmap.get(list.getKey()));
					dequy(hmap, list.getValue().getId(), c + "---", url, info);
				}
			}
		}
	}

}
