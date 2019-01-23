package com.FCdemo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.FCdemo.model.NhanSu;
import com.FCdemo.service.readExcelFile;
import com.FCdemo.setting.dbQLLoaiDAO;
import com.FCdemo.setting.dbQLNhanSuDAO;

@Controller
@RequestMapping("/Employee")
// @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER ADMIN')")
public class employeecontroller {
	ApplicationContext context = new ClassPathXmlApplicationContext("FCdemoJDBC-servlet.xml");
	dbQLNhanSuDAO dbQLNhanSu = (dbQLNhanSuDAO) context.getBean("databaseQLNhanSu");
	dbQLLoaiDAO dbQLLoai = (dbQLLoaiDAO) context.getBean("databaseQLLoai"); // lay danh sach loai tu bang qlloai

	public List<String> getKind() {
		List<String> listKind = dbQLLoai.getlistKind();
		if (listKind.isEmpty()) {
			System.out.println("k co list");
		} else {
			System.out.println("co list");
		}
		return listKind;
	}

	public List<String> checkValid(String name, String age) {
		List<String> error = new ArrayList<String>();

		if (name.trim().isEmpty()) {
			error.add("Name is not null!!!");
		}
		try {
			int Age = Integer.parseInt(age);
			if (Age <= 0) {
				error.add("Age is positive!!!");
			}
		} catch (NumberFormatException e) {
			error.add("Age is a number!!!");
		}
		return error;
	}

	@GetMapping("/addEmployee")
	public String addEmp(Model model) {
		model.addAttribute("listKind", dbQLLoai.getlistKind());// getKind());
		List<NhanSu> list = dbQLNhanSu.getlist();
		model.addAttribute("list", list);
		return "addEmp2";
	}

	// @ExceptionHandler(org.springframework.validation.BindException.class)
	// private ModelAndView processInvalidData(BindException ex) {
	// StringBuilder error = new StringBuilder();
	// for (ObjectError objectError: ex.getAllErrors()) {
	// error.append(objectError.getDefaultMessage());
	// error.append("<br/>");
	// }
	// ModelAndView model = new ModelAndView("error");
	// model.addObject("error", error);
	// return model;
	// }

	@PostMapping("/addEmpResult")
	public String addEmpRes(@RequestParam("Avatar") CommonsMultipartFile commonsMultipartFiles,
			@RequestParam("Name") String name, @RequestParam("Age") String age, @RequestParam("Sex") String sex,
			@RequestParam("KindID") String kind, Model model) throws ClassNotFoundException, SQLException, IOException {
		String message = "";
		byte[] bytes;
		List<NhanSu> addDone = new ArrayList<NhanSu>();
		List<NhanSu> addFail = new ArrayList<NhanSu>();
		List<String> exeption = checkValid(name, age);
		if (exeption.isEmpty()) {
			NhanSu temp = new NhanSu();
			temp.setName(name);
			temp.setAge(Integer.parseInt(age));
			temp.setKindID(kind);
			temp.setSex(sex);

			if (!commonsMultipartFiles.isEmpty()) {
				bytes = commonsMultipartFiles.getBytes();
				temp.setAvatar(dbQLNhanSu.encodebase64(bytes));
				// emp.setAvatar(dbQLNhanSu.encodebase64(bytes));
			}

			int a = dbQLNhanSu.add(temp);
			if (a == -1) {
				message = "Employee was in database!!!";
				addFail.add(temp);

			} else if (a == 0) {
				message = "Insert error!!!Again!!!";
				addFail.add(temp);
			} else
				message = "Insert done!";
			addDone.add(temp);
		} else {
			for (String string : exeption) {
				message = message.concat("\n" + string);
			}
		}

		model.addAttribute("listKind", dbQLLoai.getlistKind());// getKind());
		model.addAttribute("message", message);
		model.addAttribute("listDone", addDone);
		model.addAttribute("listFail", addFail);
		return "addEmp2";
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
	public String updateEmp(Model model) {
		List<NhanSu> list = dbQLNhanSu.getlist();
		model.addAttribute("list", list);
		model.addAttribute("listKind", getKind());
		return "viewUpdateEmp";
	}

	@RequestMapping(value = "/updateEmpResult", method = RequestMethod.POST)
	public String updateEmpRes(@RequestParam("Avatar") CommonsMultipartFile commonsMultipartFiles,
			@RequestParam("ID") String iD, @RequestParam("Name") String name, @RequestParam("Age") String age,
			@RequestParam("Sex") String sex, @RequestParam("KindID") String kind, Model model) {
		String message = "";
		List<String> error = new ArrayList<String>();
		int id = 0;
		try {
			id = Integer.parseInt(iD);
		} catch (NumberFormatException e) {
			message = "ID is positive Number!!!";
		}
		if (id > 0) {
			error = checkValid(name, age);
			if (error.isEmpty()) {
				byte[] bytes;
				NhanSu temp = new NhanSu();

				if (!commonsMultipartFiles.isEmpty()) {
					bytes = commonsMultipartFiles.getBytes();
					try {
						temp.setAvatar(dbQLNhanSu.encodebase64(bytes));
					} catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}

				}
				temp.setID(id);
				temp.setName(name);
				temp.setAge(Integer.parseInt(age));
				temp.setKindID(kind);
				temp.setSex(sex);

				int a = dbQLNhanSu.update(temp);
				if (a == -1) {
					message = "Employee wasn't in database!!!";
				} else if (a == 0) {
					message = "Update error!!!Again!!!";
				} else
					message = "Update done!";
			} else {
				for (String string : error) {
					message = message.concat("\n" + string);
				}
			}
		} else {
			List<String> a = checkValid(name, age);
			error.addAll(a);
			message = "ID is invalid!!!	ID is positive number!!!";
			for (String string : error) {
				message = message.concat("\t" + string);
			}
		}

		model.addAttribute("listKind", getKind());
		model.addAttribute("message", message);
		List<NhanSu> list = dbQLNhanSu.getlist();
		model.addAttribute("list", list);
		return "viewUpdateEmp";

	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public String deleteEmp(Model model) {
		List<NhanSu> list = dbQLNhanSu.getlist();
		model.addAttribute("list", list);
		model.addAttribute("employee", new NhanSu());
		return "viewDeleteEmp";
	}

	@RequestMapping(value = "/getimage/{ID}", method = RequestMethod.GET)
	public String getimage1(@PathVariable("ID") int id, Model model) throws IOException {
		model.addAttribute("image", dbQLNhanSu.getID(id).getAvatar());
		model.addAttribute("ID", id);
		return "findimage";
	}

	@RequestMapping(value = "/addExcelRes", method = RequestMethod.POST)
	public String addEmpResExcel(@RequestParam("excel") CommonsMultipartFile commonsMultipartFile, Model model)
			throws ClassNotFoundException, SQLException, IOException, InvalidFormatException {
		long start = System.currentTimeMillis();
		InputStream is = commonsMultipartFile.getInputStream();
		// File temp=new File("C:\\Users\\Khac Kien\\Desktop\\a.xlsx");
		// String path=temp.getAbsolutePath();
		// path.substring(0, path.length()-1)
		String fileLocation = commonsMultipartFile.getOriginalFilename();
		FileOutputStream fos = new FileOutputStream(fileLocation);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int ch = 0;
		while ((ch = is.read()) != -1)
			bos.write(ch);

		bos.flush();
		fos.flush();
		bos.close();
		fos.close();
		readExcelFile ex = new readExcelFile();
		// File file=new File(fileLocation);
		// System.out.println(file.getAbsolutePath());
		List<NhanSu> list = ex.getlistfromExcel(new File(fileLocation));

		List<NhanSu> addDone = new ArrayList<NhanSu>();
		List<NhanSu> addFail = new ArrayList<NhanSu>();

		Map<String, String> map = new HashMap<String, String>();
		List<String> temp = getKind();
		for (String string : temp) {
			map.put(string, string);
		}
		for (NhanSu emp : list) {
			try {
				if (map.containsValue(emp.getKindID())) {
					int a = dbQLNhanSu.add(emp);
					if (a == 1) {
						addDone.add(emp);
					} else {
						addFail.add(emp);
					}
				} else
					addFail.add(emp);
			} catch (NumberFormatException e) {
				addFail.add(emp);
			}

		}

		long end = System.currentTimeMillis();

		String done = "Số bản ghi thêm thành công: " + addDone.size();
		String lose = "Số bản ghi thất bại: " + addFail.size();
		String time = "Thời gian thực hiện: " + (end - start) + "ms";
		System.out.println(done);
		System.out.println(lose);
		model.addAttribute("listDone", addDone);
		model.addAttribute("listFail", addFail);
		model.addAttribute("time", time);
		model.addAttribute("done", done);
		model.addAttribute("fail", lose);

		return "addEmp2";
	}

}
