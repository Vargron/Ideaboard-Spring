package com.erice.Belt.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erice.Belt.libraries.boblib;
import com.erice.Belt.models.User;
import com.erice.Belt.services.UserService;

@Controller
public class UserController {
	private UserService userservice;

	public UserController( UserService userservice) {
		this.userservice=userservice;
		
	}
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "login/index.jsp";
		
	}

	private boolean checkuniqueemail(String email) {
		List<User> users=userservice.allusers();
		for (int i=0; i<users.size();i++) {
			if (users.get(i).getEmail().equals(email)) {
				return false;
			}
		}
		return true;
		
		
	}
	
	@PostMapping("/register/")
	public String registerform(Model model,@Valid @ModelAttribute("user") User user,BindingResult result, @RequestParam("pword")String pword, @RequestParam("pwordc")String pwordc,@RequestParam("alias")String alias) {
		System.out.println("passwords:"+pword+pwordc+"email"+user.getEmail()+"name"+user.getFirst_name()+user.getLast_name());
		List<String>myerrors=new ArrayList<String>();
		if (!pword.equals(pwordc)) {
			myerrors.add("passwords must match");
		}
		if(pword.length()<8) {
			myerrors.add("password must be at least 8 characters");
		}
		List<String>mydata=new ArrayList(Arrays.asList(pword,pwordc, user.getFirst_name(),user.getLast_name(), alias));
		int spacefail=0;
		for (int i=0; i<mydata.size();i++) {
			if (!boblib.checkspaces((mydata.get(i)))) {
				spacefail+=1;
				
			}

		}

		if (spacefail>0) {
			myerrors.add("spaces are invalid characters");
		}
		if (!(boblib.checkemail(user.getEmail()))) {
			myerrors.add("email invalid");
		}
		if (!(checkuniqueemail(user.getEmail()))) {
			myerrors.add("email already in use try again");
		}
		if (result.hasErrors()||myerrors.size()>0) {
			List<ObjectError>syserrors=result.getAllErrors();
			for (int i=0; i <syserrors.size();i++) {
				myerrors.add(syserrors.get(i).getDefaultMessage());
			}
			
			System.out.println(myerrors);
			model.addAttribute("errors", myerrors);
			return"login/index.jsp";
		}
		String hashedpass=BCrypt.hashpw(pword, BCrypt.gensalt());
		System.out.println(hashedpass);
		User bob=new User(user.getFirst_name(),user.getLast_name(),user.getEmail(),hashedpass, alias);
		userservice.adduser(bob);
		
		
		return "redirect:/landing/";
	}

	@RequestMapping("/landing/")
	public String landing() {
		return "login/registrationsucess.jsp";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session,@RequestParam("email") String email, @RequestParam("password") String password) {
	
	try{
		User target=userservice.findbyemail(email);
		System.out.println(BCrypt.checkpw(password, target.getPwordhash()));
		if(BCrypt.checkpw(password, target.getPwordhash())){
			System.out.println("sucess");
			
			session.setAttribute("user", target);
			
			
			return "redirect:/idea/";
		}
	}catch(Exception e){
		return "redirect:/";
	}

	return "redirect:/";
	}
	
	
	@RequestMapping("/user/{id}/view")
	public String view_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		if (!boblib.checkloggedin(session)) {
			return "redirect:/";
		}
		model.addAttribute("target", userservice.getuser(id));
		
		return "user/viewuser.jsp";
	}
	@RequestMapping("/logout/")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		return "redirect:/";
	}
		

}
