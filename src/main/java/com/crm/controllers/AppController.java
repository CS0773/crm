package com.crm.controllers;

import com.crm.model.Leads;
import com.crm.model.Member;
import com.crm.model.Opportunity;
import com.crm.model.User;
import com.crm.service.LeadRepository;
import com.crm.service.MemberRepository;
import com.crm.service.OpportunityRepository;
import com.crm.service.UserRepository;
import com.crm.model.*;
import com.crm.service.*;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private ProductRepository productRepo;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}


}
