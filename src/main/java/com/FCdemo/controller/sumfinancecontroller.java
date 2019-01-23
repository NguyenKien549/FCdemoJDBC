package com.FCdemo.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.FCdemo.model.Sum;
import com.FCdemo.setting.dbQLLoaiDAO;
import com.FCdemo.setting.dbSumDAO;

@Controller
@SessionAttributes("user")
public class sumfinancecontroller {
	ApplicationContext cnt= new ClassPathXmlApplicationContext("FCdemoJDBC-servlet.xml");
	dbSumDAO sumDAO=(dbSumDAO) cnt.getBean("sum");
	dbQLLoaiDAO dbQLLoai=(dbQLLoaiDAO) cnt.getBean("databaseQLLoai"); //lay danh sach loai tu bang qlloai
	
	@RequestMapping(value="/listfinance",method=RequestMethod.GET)
	public String viewsum(Model model) {
		
		int numberItems=  5;//Integer.parseInt(request.getParameter("items"));
		int currPage=1;//Integer.parseInt(request.getParameter("currentPage"));
		List<Sum> list=sumDAO.getPageSum(numberItems, currPage);
		//List<Sum> list=sumDAO.filterEMP();
//		if(list.isEmpty()) {
//			model.addAttribute("message","Data is empty");
//		}
		List<String> listKind=dbQLLoai.getlistKind();
		if (listKind.isEmpty()) {
			System.out.println("k co list");
		} else {
			System.out.println("co list");
		}
		model.addAttribute("listKind", listKind);
		model.addAttribute("listEmp",list);
		return "listSum_js";
	}
	
//	@RequestMapping(value="/searchAll",method=RequestMethod.POST)
//	public String searchAllview(
//			Model model) {
//		List<Sum> list=sumDAO.filter();
//		List<String> listKind=dbQLLoai.getlistKind();
//		if (listKind.isEmpty()) {
//			System.out.println("k co list");
//		} else {
//			System.out.println("co list");
//		}
//		model.addAttribute("listKind", listKind);
//		model.addAttribute("list",list);
//		return "searchAll";
//	}
//	
//	@RequestMapping(value="/searchSumAll",method=RequestMethod.POST)
//	public String searchAll(@RequestParam("name")String name,@RequestParam("type") String type,
//			Model model) {
//		System.out.println("vao ham");
//		List<Sum> list=sumDAO.filterEMP(name, type);
//		
//		List<String> listKind=dbQLLoai.getlistKind();
//		if (listKind.isEmpty()) {
//			System.out.println("k co list");
//		} else {
//			System.out.println("co list");
//		}
//		model.addAttribute("listKind", listKind);
//		model.addAttribute("list",list);
//		return "searchAll";
//	}
}
//search dung onkeyup js