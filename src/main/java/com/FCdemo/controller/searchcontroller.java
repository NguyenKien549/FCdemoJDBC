package com.FCdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.FCdemo.model.NhanSu;
import com.FCdemo.setting.dbQLLoaiDAO;
import com.FCdemo.setting.dbQLNhanSuDAO;
import com.FCdemo.setting.dbSumDAO;
import com.FCdemo.setting.searchListDAO;

@Controller
@SessionAttributes("user")
public class searchcontroller {
	ApplicationContext context=new ClassPathXmlApplicationContext("FCdemoJDBC-servlet.xml");
	searchListDAO searchDAO=(searchListDAO) context.getBean("search");
	dbQLNhanSuDAO dbQLNhanSu=(dbQLNhanSuDAO) context.getBean("databaseQLNhanSu");
	dbSumDAO sumDAO=(dbSumDAO) context.getBean("sum");
	dbQLLoaiDAO dbQLLoai=(dbQLLoaiDAO) context.getBean("databaseQLLoai"); //lay danh sach loai tu bang qlloai
	
	
	@RequestMapping("/search")
	public String viewsearch(Model model,HttpServletRequest request) {
		List<String> listSex=new ArrayList<String>();
		listSex.add("Male");
		listSex.add("Female");
		listSex.add("Other");
		model.addAttribute("listSex", listSex);
//		List<String> listKind=dbQLLoai.getlistKind();
//		if (listKind.isEmpty()) {
//			System.out.println("k co list");
//		} else {
//			System.out.println("co list");
//		}
//		model.addAttribute("listKind", listKind);
		
		int numberItems=  7;//Integer.parseInt(request.getParameter("items"));
		int currPage=1;//Integer.parseInt(request.getParameter("currentPage"));
		List<NhanSu> pageEmp=dbQLNhanSu.getPage(numberItems, currPage);
		
		int total=dbQLNhanSu.getNumbEmp();
		if(total % numberItems ==0) {
			total /= numberItems;
		}else total = total/numberItems +1;
		request.setAttribute("total", total);
		//request.setAttribute("", o);
		//List<NhanSu> list=dbQLNhanSu.getlist();
		model.addAttribute("list",pageEmp);
		return "viewSearch";
	}
	
	//search by name
//	@RequestMapping("/searchbyName")
//	public String searchName(Model model) {
//		model.addAttribute("employee",new NhanSu());
//		return "viewSearchByName";
//	}
	
//	@RequestMapping(value="/searchbyNameResult",method=RequestMethod.POST)
//	public String searchbyNameRes(@ModelAttribute("employee")NhanSu nhanSu,@RequestParam("Name")String name,Model model) {
//		List<NhanSu> list=searchDAO.searchbyName(name.trim());
//		String mess;
//		if(list!=null) {
//			mess="Search done!!!";
//		}else mess="No data!!!";
//		model.addAttribute("message",mess);
//		model.addAttribute("list", list);
//		return "viewSearchByName";
//	}
	
//	//pagination
//	@RequestMapping(value= {"/load"},method=RequestMethod.POST)
//	public @ResponseBody String getdata(HttpServletRequest request) {
//		 //List<Entity> entityList = entityManager.findAll();
//		int numberItems = Integer.parseInt(request.getParameter("items"));
//		int currPage= Integer.parseInt(request.getParameter("currentPage"));
//		List<NhanSu> pageEmp=dbQLNhanSu.getPage(numberItems, currPage);
//		
//		int total=dbQLNhanSu.getNumbEmp();
//		if(total % numberItems ==0) {
//			total /= numberItems;
//		}else total = total/numberItems +1;
//		
//		//request.setAttribute("data", pageEmp);
//		request.setAttribute("total", total);
//		ObjectMapper obj=new ObjectMapper();
//		String ajaxResponse = "";
////		if(type.equals("getdata")) {
//			for (NhanSu emp : pageEmp) {
//				try {
//					ajaxResponse=obj.writeValueAsString(emp);
//				} catch (JsonProcessingException e) {
//					e.printStackTrace();
//					
//				}
//			}
////		}
//		System.out.println(ajaxResponse);
//		
//		return ajaxResponse;
//	}
//	
//						//AJAX SEARCH
//	@RequestMapping(value="/getlistSum",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
//	public @ResponseBody String getSum(Model model) throws JsonProcessingException {
//		List<Sum> list=sumDAO.filter();
//		
//		ObjectMapper obj=new ObjectMapper();
//		
//		String size=obj.writeValueAsString(list);
//		return size;
//	}
	
}
