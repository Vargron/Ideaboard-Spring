package com.erice.Belt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erice.Belt.libraries.boblib;
import com.erice.Belt.models.Idea;
import com.erice.Belt.models.User;
import com.erice.Belt.services.IdeaService;
import com.erice.Belt.services.UserService;

@RequestMapping("/idea")
@Controller
public class IdeaController {
	private IdeaService iserve;
	private UserService userve;
	public IdeaController(IdeaService iserve) {
		this.iserve=iserve;
	}
	

	@RequestMapping("/")
	public String dashboard(HttpSession session, Model model ) {
		if (!boblib.checkloggedin(session)) {
			return "redirect:/";
		}
		model.addAttribute("idea", new Idea());
		model.addAttribute("user", session.getAttribute("user"));
		
		
	
		model.addAttribute("ideas", iserve.allideas());
		return "idea/dashboard.jsp";
		
	}
	
	@PostMapping("/new/")
	public String new_idea(HttpSession session,@RequestParam("ideatext") String ideatext, @RequestParam("user_id") String uid) {
		if (!boblib.checkloggedin(session)) {
			return "redirect:/";
		}
		User bob=(User)session.getAttribute("user");
		
		
		iserve.addidea(new Idea(ideatext,bob));
		System.out.println(Long.parseLong(uid));
		
		return "redirect:/idea/";
	}
	
	@RequestMapping("/{id}/view")
	public String view_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		if (!boblib.checkloggedin(session)) {
			return "redirect:/";
		}
		model.addAttribute("idea", iserve.getidea(id));
		model.addAttribute("user", session.getAttribute("user"));
		return "idea/viewidea.jsp";
	}
	
	@RequestMapping("/{id}/like/")
	public String like_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		if (!boblib.checkloggedin(session)) {
			return "redirect:/";
		}
		iserve.likeidea(iserve.getidea(id), (User)session.getAttribute("user"));

		return "redirect:/idea/";
	}
	
	@RequestMapping("/{id}/delete/")
	public String delete_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		if (!boblib.checkloggedin(session)) {
			return "redirect:/";
		}
		if(((User)session.getAttribute("user")).getId()==iserve.getidea(id).getAuthor().getId()) {
			iserve.deleteidea(iserve.getidea(id));
		}
		

		return "redirect:/idea/";
	}
	
	
	
	
	

}
