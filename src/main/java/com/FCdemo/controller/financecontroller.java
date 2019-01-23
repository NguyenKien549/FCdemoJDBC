package com.FCdemo.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.FCdemo.model.TaiChinh;
import com.FCdemo.setting.dbQLLoaiDAO;
import com.FCdemo.setting.dbQLTaiChinhDAO;

@Controller
@RequestMapping("/finance")
@SessionAttributes("user")
public class financecontroller {
	ApplicationContext context=new ClassPathXmlApplicationContext("FCdemoJDBC-servlet.xml");
	dbQLTaiChinhDAO taichinhDAO=(dbQLTaiChinhDAO) context.getBean("databaseQLTaiChinh");
	dbQLLoaiDAO dbQLLoai=(dbQLLoaiDAO) context.getBean("databaseQLLoai"); //lay danh sach loai tu bang qlloai
	
	
	public String checkFinance(TaiChinh fin,Model model) {
		List<String> error=new ArrayList<String>();
		if(fin.getName().trim().isEmpty()) {
			error.add("Name is not null");
		}
		if(fin.getAmount()<=0) {
			error.add("Amount is positive");
		}
		model.addAttribute("error", error);
		return "errorFinance";
	}
	
	@RequestMapping(value="/addFinance")
	public String addFinance(Model model) {
		model.addAttribute("finance",new TaiChinh());
		List<String> listKind=dbQLLoai.getlistKind();
		if (listKind.isEmpty()) {
			System.out.println("k co list");
		} else {
			System.out.println("co list");
		}
		model.addAttribute("listKind", listKind);
		return "viewAddFinance";
	}
	
	@RequestMapping(value="/addFinanceResult",method=RequestMethod.POST)
	public String addFinanceRes(@ModelAttribute("finance") TaiChinh taiChinh,Model model) {
		if(!taiChinh.getName().trim().isEmpty() && taiChinh.getAmount()>0) {
		int a=taichinhDAO.addTaiChinh(taiChinh);
		String message;

		if(a==0) {
			message="Insert error!!!Again!!!";
		}
		else message="Insert done!";
		List<String> listKind=dbQLLoai.getlistKind();
		if (listKind.isEmpty()) {
			System.out.println("k co list");
		} else {
			System.out.println("co list");
		}
		model.addAttribute("listKind", listKind);
		model.addAttribute("message", message);
		
		return "viewAddFinance";
		}else return checkFinance(taiChinh, model);
	}
	
	@RequestMapping(value="/updateFinance")
	public String updateFinance(Model model) {
		model.addAttribute("finance",new TaiChinh());
		List<String> listKind=dbQLLoai.getlistKind();
		if (listKind.isEmpty()) {
			System.out.println("k co list");
		} else {
			System.out.println("co list");
		}
		model.addAttribute("listKind", listKind);
		
		List<TaiChinh> list=taichinhDAO.getlist();
		model.addAttribute("list",list);
		return "viewUpdateFinance";
	}
	
	@RequestMapping(value="/updateFinanceResult",method=RequestMethod.POST)
	public String updateFinanceRes(@ModelAttribute("finance") TaiChinh taiChinh,Model model) {
		if(!taiChinh.getName().trim().isEmpty() && taiChinh.getAmount()>0) {
			System.out.println("a:" +taiChinh.getKindID());
		int a=taichinhDAO.updateTaiChinh(taiChinh);
		String message;

		if(a==0) {
			message="Update error!!!Again!!!";
		}
		else message="Update done!";
		List<String> listKind=dbQLLoai.getlistKind();
		if (listKind.isEmpty()) {
			System.out.println("k co list");
		} else {
			System.out.println("co list");
		}
		List<TaiChinh> list=taichinhDAO.getlist();
		model.addAttribute("list",list);
		model.addAttribute("listKind", listKind);
		model.addAttribute("message", message);
		return "viewUpdateFinance";
		}else return checkFinance(taiChinh, model);
	}
	
	@RequestMapping(value="/deleteFinance",method=RequestMethod.GET)
	public String deleteFinance(Model model) {
		List<TaiChinh> list=taichinhDAO.getlist();
		model.addAttribute("list",list);
		
		return "viewDeleteFinance";
	}
	
//	@RequestMapping(value="/deleteFinanceResult/{ID}",method=RequestMethod.GET)
//	public String deleteFinanceRes(@PathVariable("ID")int id,Model model) {
//		
//		String message;
//		int del=taichinhDAO.deleteTaiChinh(id);
//
//		if(del==null) {
//			message="Delete error!!!Again!!!";
//		}
//		else message="Delete done!";
//		
//		List<TaiChinh> list=taichinhDAO.getlist();
//		model.addAttribute("list",list);
//		model.addAttribute("message", message);
//		return "viewDeleteFinance";
//	}
}
