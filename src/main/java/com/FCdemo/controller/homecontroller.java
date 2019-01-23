package com.FCdemo.controller;

import java.util.ArrayList;



import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.FCdemo.model.Account;
import com.FCdemo.setting.dbAccountDAO;
import com.FCdemo.setting.dbQLRoleDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes("user")
public class homecontroller {
	
	ApplicationContext context=new ClassPathXmlApplicationContext("FCdemoJDBC-servlet.xml");
	dbAccountDAO dbAccount=(dbAccountDAO) context.getBean("account");
	dbQLRoleDAO dbRole=(dbQLRoleDAO) context.getBean("QLRole");
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	public List<String> getListPrincipal(){
		
		final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		List<String> usersNamesList = new ArrayList<String>();
        for (final Object principal : allPrincipals) {
            	 if (principal instanceof UserDetails) {
            	        usersNamesList.add(((UserDetails) principal).getUsername());
            	        System.out.println("active:" +((UserDetails) principal).getUsername());
            	    }else {
            	    	System.out.println("unactive");
            	    }
            }
        return usersNamesList;
	}
	
	
	@GetMapping({"login","/"})
	public String homeview(Model model,@RequestParam(value = "error", required = false) final String error) {
		if (getPrincipal()!=null) {
			return "redirect: FCmanager";
		}
		System.out.println("abc:"+getPrincipal());
		model.addAttribute("listRole",dbRole.getRole());
		if(error!=null)
			model.addAttribute("message","Login Fail!!! Again please!!!");
		return "login";
	}
	
	@GetMapping(value="/register",produces = "application/json; charset=utf-8")
	public @ResponseBody String register(@RequestParam("name") String name,@RequestParam("mail") String mail,@RequestParam("role")String role
			,@RequestParam("username") String username,@RequestParam("password") String password) throws JsonProcessingException {
		String a=dbAccount.createAccount(new Account(name,mail,username,password));
		dbRole.createRole(username, role);
		ObjectMapper obj=new ObjectMapper();
		String data=obj.writeValueAsString(a);
			return data;
	}
	
	@GetMapping({"/FCmanager"})
	public String home(ModelMap modelMap){
				modelMap.put("user", getPrincipal());
				System.out.println("user:"+ getPrincipal());
				return "home";
	}
	
	@GetMapping("/information")
	public String getinfor(ModelMap modelMap) {
		getListPrincipal();
//		UserDetails a=(UserDetails) getPrincipal();
//		modelMap.put("info",dbAccount.getAccount(a.getUsername(),a.getPassword()));
			return "information";
	}
	
	@GetMapping("/getAccount")
	public String getUser(ModelMap modelMap) {
		List<String>a=getListPrincipal();
		modelMap.addAttribute("listUser",a);
			return "TrackUser";
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request,response,auth);
			}
//		try {
//				session.invalidate();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		}
	  return "redirect: login";
	}
	
	@GetMapping("/error")
	public String getError() {
			return "error";
	}
	
	public Object getPrincipal() {
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails) principal);
		}
		return null; //neu khong tra ve anonymousUser
	}
}
