package springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springmvc.bean.Categories;
import springmvc.bean.Products;
import springmvc.constant.Defines;
import springmvc.dao.CategoryDAO;
import springmvc.dao.ProductsDAO;
import springmvc.dao.WarehouseDAO;

@Controller
@RequestMapping("admin/prod")
public class AdminProdController {
	
	@Autowired
	private Defines defines;
	
	@Autowired
	private ProductsDAO productsDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private WarehouseDAO warehouseDAO;
	
	private List<String> listMenu = new ArrayList<String>();
	
	private Integer id_prod;
	
	public Integer getId_prod() {
		return id_prod;
	}

	public void setId_prod(Integer id_prod) {
		this.id_prod = id_prod;
	}
	
	@ModelAttribute
	public void addCommon(ModelMap map) {
		map.addAttribute("defines", defines);
		map.addAttribute("listProds",productsDAO.getItems());
	}
	
	@RequestMapping(value="")
	public String indexProd(ModelMap map) {
		return "admin.prod.index";	
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map,HttpServletRequest request) {
		HashMap<Integer, Categories> hmap = new HashMap<Integer, Categories>();
		List<Categories> listItem = null;
		listItem = categoryDAO.getItem();
		String urlHeader = request.getContextPath();
		for (Categories item : listItem) {
			Categories cat = new Categories(item.getId(), item.getId_parent(), item.getName());
			hmap.put(item.getId(), cat);
		}
		dequy(hmap, 0, "", urlHeader, "add");
		map.addAttribute("listMenu", listMenu);
		return "admin.prod.add";
		
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute("Products") Products products,@RequestParam("hinhanh") CommonsMultipartFile cmf,HttpServletRequest request,RedirectAttributes rs) {
		/*Get time now*/
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		String day  = dateFormat.format(date);
		String arrDay[] = day.split(" ");
		String timeNow="";
		for(String item : arrDay) {
			timeNow+=item.replaceAll("[^0-9.]", "");
		}
		String filename = cmf.getOriginalFilename();
		filename = timeNow+"-"+filename;
		String appPath = request.getServletContext().getRealPath("");
		String dirPath = appPath+Defines.DIR_UPLOAD;
		if(!"".equals(filename)) {
			File createDir = new File(dirPath);
			if(!createDir.exists()) {
				createDir.mkdirs();
			}
			String filePath = dirPath+File.separator+filename;
			System.out.println(filePath);
			try {
				Products prod = new Products(0,products.getName(),products.getId_cat(),products.getPrice(),products.getSale(),time,filename,1,products.getDescription(),0,"");
				if(productsDAO.addItem(prod) > 0) {
					rs.addFlashAttribute("msg",defines.SUCCESS);
					cmf.transferTo(new File(filePath));
				}else {
					rs.addFlashAttribute("err",defines.ERROR);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/prod";
	}
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String edit(HttpServletRequest request,ModelMap map,@PathVariable(value="id",required=false) Integer id) {
		HashMap<Integer, Categories> hmap = new HashMap<Integer, Categories>();
		List<Categories> listItem = null;
		listItem = categoryDAO.getItem();
		String urlHeader = request.getContextPath();
		for (Categories item : listItem) {
			Categories cat = new Categories(item.getId(), item.getId_parent(), item.getName());
			hmap.put(item.getId(), cat);
		}
		dequy(hmap, 0, "", urlHeader, "add");
		
		map.addAttribute("listMenu", listMenu);
		map.addAttribute("prod",productsDAO.getItem(id));
		return "admin.prod.edit";
	}
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,@RequestParam("hinhanh") CommonsMultipartFile cmf,RedirectAttributes rs,ModelMap map,@PathVariable(value="id",required=false) Integer id,@ModelAttribute("products") Products products) {
		String filename="";
		
		String filePicOld = productsDAO.getItem(id).getPicture();
		String filePIcNew = cmf.getOriginalFilename();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		String day  = dateFormat.format(date);
		if(filePIcNew != "") {
			String arrDay[] = day.split(" ");
			String timeNow="";
			for(String item : arrDay) {
				timeNow+=item.replaceAll("[^0-9.]", "");
			}
			filename = timeNow+"-"+filePIcNew;
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath+Defines.DIR_UPLOAD;
			File createDir = new File(dirPath);
			if(!createDir.exists()) {
				createDir.mkdirs();
			}
			String filePath = dirPath+File.separator+filename;
			try {
				cmf.transferTo(new File(filePath));
				File file = new File(filePicOld);
				file.delete();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}else {
			filename = filePicOld;
		}
		Products prod = new Products(id,products.getName(),products.getId_cat(),products.getPrice(),products.getSale(),time,filename,productsDAO.getItem(id).getStatus(),products.getDescription(),1,"");
		if(productsDAO.updateItem(prod) > 0) {
			rs.addFlashAttribute("msg",defines.SUCCESS);
		}else {
			rs.addFlashAttribute("err",defines.ERROR);
		}
		return "redirect:/admin/prod";
	}
	@RequestMapping("del/{id}")
	public String del(@PathVariable(value="id",required=false) Integer id,RedirectAttributes rs) {
		if(productsDAO.delItem(id) > 0) {
			rs.addFlashAttribute("msg",defines.SUCCESS);
			if(warehouseDAO.getitems(id) != null) {
				warehouseDAO.delItem(id);
			}
		}else {
			rs.addFlashAttribute("err",defines.ERROR);
		}
		return "redirect:/admin/prod";
	}
	@RequestMapping(value="update/{id}",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public String updateQty(@PathVariable (value="id",required=false) Integer id,@RequestParam("qty") Integer qty) {
		System.out.println(id);
		System.out.println(qty);
		if(productsDAO.getItem(id)  != null) {
			if(warehouseDAO.getitems(id) != null) {
				warehouseDAO.update(id, qty);
			}else {
				warehouseDAO.additems(id, qty);
			}
		}
		return "{\"id_prod\": \""+warehouseDAO.getitems(id).getId_prod()+"\", \"qty\": \""+warehouseDAO.getitems(id).getQuanlity()+"\"}";
	}
	public void dequy(HashMap<Integer, Categories> hmap, int key, String c, String url, String info) {
		int i = 0;
		String table = "";
		for (Map.Entry<Integer, Categories> list : hmap.entrySet()) {
				if (list.getValue().getId_parent() == key && info.equalsIgnoreCase("add")) {
					table = "<option value=" + list.getKey() + ">" + c + list.getValue().getName() + "</option>";
					listMenu.add(table);
					hmap.remove(hmap.get(list.getKey()));
					dequy(hmap, list.getValue().getId(), c + "---", url, info);
				}
			
		}
	}
	
	
}
