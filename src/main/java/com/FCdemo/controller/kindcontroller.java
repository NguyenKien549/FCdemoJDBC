package com.FCdemo.controller;

import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.FCdemo.model.Loai;
import com.FCdemo.setting.dbQLLoaiDAO;

@Controller
@RequestMapping("/type")
@SessionAttributes("user")
public class kindcontroller {
	ApplicationContext context= new ClassPathXmlApplicationContext("FCdemoJDBC-servlet.xml");
	dbQLLoaiDAO dbLoaiDAO=(dbQLLoaiDAO) context.getBean("databaseQLLoai");
	
	
	
	@RequestMapping("/addKind")
	public String addKind(Model model) {
		model.addAttribute("kind",new Loai());
		return "viewAddKind";
	}
	
	@RequestMapping(value="/addKindResult",method=RequestMethod.POST)
	public String addKindRes(@ModelAttribute("kind")Loai loai) {
		dbLoaiDAO.addLoai(loai);
		return "viewAddKind";
	}
	
	@RequestMapping("/updateKind")
	public String updateKind(Model model) {
		model.addAttribute("kind",new Loai());
		return "viewUpdateKind";
	}
	
	@RequestMapping(value="/updateKindResult",method=RequestMethod.POST)
	public String updateKindRes(@ModelAttribute("kind")Loai loai,Model model) {
		int a=dbLoaiDAO.updadeLoai(loai);
		String message;
		
		if(a==-1) {
			message="This Kind wasn't in database!!!";
		}else if(a==0) {
			message="Update error!!!Again!!!";
		}
		else {
			message="Update done!";
		}
		//List<String> listKind=dbQLLoai.getlistKind();
		//model.addAttribute("listKind", listKind);
		model.addAttribute("message", message);
		return "viewUpdateKind";
	}
	
	//bi rang buoc boi khoas ngoai sua sau
	@RequestMapping("/deleteKind")
	public String deleteKind(Model model) {
		List<Loai> list=dbLoaiDAO.getlist();
		model.addAttribute("list",list);
		model.addAttribute("kind",new Loai());
		return "viewdeleteKind";
	}
	
//	@RequestMapping(value="/deleteKindResult/{kind}",method=RequestMethod.POST)
//	public String deleteKindRes(@PathVariable("kind")String kind,Model model) {
//		dbLoaiDAO.deleteLoai(kind);
//		String message;
//		
//		if(kind==null) {
//			message="Employee wasn't in database!!!";
//		}else  message="Delete done!";
//		model.addAttribute("message", message);
//		model.addAttribute("delKind",kind);
//		return "viewdeleteKind";
//	}
}
