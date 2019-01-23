package com.FCdemo.controller;

import java.io.IOException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.FCdemo.model.Loai;
import com.FCdemo.model.NhanSu;
import com.FCdemo.model.Sum;
import com.FCdemo.model.TaiChinh;
import com.FCdemo.setting.dbQLLoaiDAO;
import com.FCdemo.setting.dbQLNhanSuDAO;
import com.FCdemo.setting.dbQLTaiChinhDAO;
import com.FCdemo.setting.dbSumDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class paginationcontroller {
	ApplicationContext context = new ClassPathXmlApplicationContext("FCdemoJDBC-servlet.xml");
	dbQLNhanSuDAO dbQLNhanSu = (dbQLNhanSuDAO) context.getBean("databaseQLNhanSu");
	dbSumDAO sumDAO = (dbSumDAO) context.getBean("sum");
	dbQLTaiChinhDAO dbFinance = (dbQLTaiChinhDAO) context.getBean("databaseQLTaiChinh");
	dbQLLoaiDAO dbLoai = (dbQLLoaiDAO) context.getBean("databaseQLLoai");

//	@RequestMapping(value = "/count", method = RequestMethod.GET)
//	public @ResponseBody String total(@RequestParam("items") int numb, Model model) throws JsonProcessingException {
//
//		int total = dbQLNhanSu.getNumbEmp();
//		if (total % numb == 0)
//			total /= numb;
//		else
//			total = total / numb + 1;
//		ObjectMapper obj = new ObjectMapper();
//		String size = obj.writeValueAsString(total);
//		return size;
//	}

//	@RequestMapping(value = "/loadpage", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
//	public @ResponseBody String getData(@RequestParam("items") int numb, @RequestParam("currentPage") int page)
//			throws JsonProcessingException {
//		List<NhanSu> total = dbQLNhanSu.getPage(numb, page);
//
//		ObjectMapper obj = new ObjectMapper();
//
//		return obj.writeValueAsString(total);
//	}

	@RequestMapping(value = "/countSum", method = RequestMethod.GET)
	public @ResponseBody String totalSum(@RequestParam("items") String numb, Model model)
			throws JsonProcessingException {
		int a = Integer.parseInt(numb);
		int total = sumDAO.getNumbSum();
		if (total % a == 0)
			total /= a;
		else
			total = total / a + 1;
		ObjectMapper obj = new ObjectMapper();
		String size = obj.writeValueAsString(total);
		return size;
	}

	@GetMapping(value = "/loadSum", produces = "application/json; charset=utf-8")
	public @ResponseBody String getDataSum(@RequestParam("items") String numb, @RequestParam("currentPage") int page,
			Model model) throws JsonProcessingException {
		List<Sum> list = sumDAO.getPageSum(Integer.parseInt(numb), page);
		double sum = 0;
		DecimalFormat df = new DecimalFormat(".###");
		for (Sum temp : list) {
			sum += temp.getAmount();
		}
		List<Object> temp = new ArrayList<Object>();
		temp.add(df.format(sum));
		temp.addAll(list);
		System.out.println("sum:" + df.format(sum));

		ObjectMapper obj = new ObjectMapper();
		String size = obj.writeValueAsString(temp);
		return size;
	}

	/*
	 * ================================ AJAX DELETE ================================
	 */
	@RequestMapping(value = "/deleteEmpAjax", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String getdelete(@RequestParam(value = "id[]") String[] id) { // co the dung Integer[]
		ObjectMapper obj = new ObjectMapper();
		String size = null;
		try {
			System.out.println(id.length);
			int length = id.length;
			for (int i = 0; i < length; i++) {
				dbQLNhanSu.delete(Integer.parseInt(id[i]));
			}
			List<NhanSu> list = dbQLNhanSu.getlist();
			size = obj.writeValueAsString(list);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return size;

	}

	@RequestMapping(value = "/deleteFinanceAjax", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String getdeleteFinance(@RequestParam(value = "id[]") int[] id)
			throws JsonProcessingException {

		for (int i = 0; i < id.length; i++) {
			dbFinance.deleteTaiChinh(id[i]);
		}
		List<TaiChinh> list = dbFinance.getlist();
		ObjectMapper obj = new ObjectMapper();

		String size = obj.writeValueAsString(list);
		return size;
	}

	@RequestMapping(value = "/deleteTypeAjax", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String getdeleteType(@RequestParam("type[]") String[] type) throws JsonProcessingException {
		for (int i = 0; i < type.length; i++) {
			dbLoai.deleteLoai(type[i]);
		}
		List<Loai> list = dbLoai.getlist();
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(list);
		return data;
	}

	@RequestMapping(value = "/countSearch", method = RequestMethod.GET)
	public @ResponseBody String totalSearch(@RequestParam("name") String name, @RequestParam("type") String type,
			@RequestParam("items") String numb, Model model) throws JsonProcessingException {
		int a = Integer.parseInt(numb);

		System.out.println(name + "\t" + type + numb);

		int total = sumDAO.getNumbSearch(name, type);

		System.out.println(total);
		if (total % a == 0)
			total /= a;
		else
			total = total / a + 1;
		ObjectMapper obj = new ObjectMapper();
		String size = obj.writeValueAsString(total);
		return size;
	}

	@GetMapping(value = "/loadSearch", produces = "application/json; charset=utf-8")
	public @ResponseBody String getDataSearch(@RequestParam("items") String numb,
			@RequestParam("currentPageSearch") int page, Model model, @RequestParam("name") String name,
			@RequestParam("type") String type) throws JsonProcessingException {
		List<Sum> list = sumDAO.getPageSearch(Integer.parseInt(numb), page, name, type);

		ObjectMapper obj = new ObjectMapper();
		String size = obj.writeValueAsString(list);
		return size;
	}

}
