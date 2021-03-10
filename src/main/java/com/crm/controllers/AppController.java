package com.crm.controllers;

import com.crm.model.Activity;
import com.crm.model.Leads;
import com.crm.model.Opportunity;
import com.crm.model.User;
import com.crm.service.ActivityRepository;
import com.crm.service.LeadRepository;
import com.crm.service.OpportunityRepository;
import com.crm.service.UserRepository;
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
	private LeadRepository leadRepo;
	@Autowired
	private OpportunityRepository opportunityRepo;

	@Autowired
	private ActivityRepository activityRepo;


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

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	//2 RESTful API methods for Retrieval operations - By Id
	@GetMapping("/users/{id}")
	public ResponseEntity<User> get(@PathVariable Integer id) {
		try {
			User user = userRepo.getOne(Long.valueOf(id));
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}


/*

	//4 RESTful API method for Update operation
	@PutMapping("/edit_user/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
		try {
			User existUser = userRepo.getOne(Long.valueOf(id));;
			userRepo.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//5 RESTful API method for Delete operation
	@DeleteMapping("/delete_user/{id}")
	public void delete(@PathVariable Integer id) {
		userRepo.deleteById(Long.valueOf(id));
	}
*/




	@GetMapping("/login_success")
	public String listUsersAfterLogin(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "login_success";
	}


	@RequestMapping("/load_edit_user/{id}")
	public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_user");
		User user = userRepo.getOne(Long.valueOf(id));;
		mav.addObject("user", user);

		return mav;
	}

	@PostMapping("/edit_user")
	public String showEditUserPage(@ModelAttribute("user") User userUpdated) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(userUpdated.getPassword());
		userUpdated.setPassword(encodedPassword);

		User user = userRepo.getOne(Long.valueOf(userUpdated.getId()));
		user.setEmail(userUpdated.getEmail());
		user.setFirstName(userUpdated.getFirstName());
		user.setPassword(userUpdated.getPassword());
		user.setLastName(userUpdated.getLastName());

		userRepo.save(user);

		return "edit_success";
	}

	@RequestMapping("/delete_user/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		userRepo.deleteById(Long.valueOf(id));
		return "delete_success";
	}

//	listing lead
	@GetMapping("/lead_list")
	public String listLeads(Model model) {
		List<Leads> listLeads = leadRepo.findAll();
		model.addAttribute("listLeads", listLeads);
		return "lead_list";
	}

//  lead creation
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Leads leads = new Leads();
		model.addAttribute("leads", leads);
		return "new_leads";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("leads") Leads leads) {
		leadRepo.save(leads);
		return "new_leads";
	}


	@GetMapping("/opportunity_page")
	public String showOpportunityForm(Model model) {
		model.addAttribute("opportunity", new Opportunity());

		return "opportunity";
	}
	@PostMapping("/process_opportunity")
	public String saveOpportunity(Opportunity opportunity) {


		opportunityRepo.save(opportunity);

		return "opportunity_success";
	}

	@GetMapping("/opportunities_page")
	public String opportunityListUsers(Model model) {
		List<Opportunity> listUsers = opportunityRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "opportunity_list";
	}

	@RequestMapping("/load_edit_opportunity/{id}")
	public ModelAndView showEditOpportunityPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_opportunity");
		Opportunity opportunity = opportunityRepo.getOne(Math.toIntExact(Long.valueOf(id)));;
		mav.addObject("opportunity", opportunity);

		return mav;
	}

	@PostMapping("/edit_opportunity")
	public String showEditOpportunityPage(@ModelAttribute("opportunity") Opportunity opportunityUpdated) {


		Opportunity opportunity = opportunityRepo.getOne(Math.toIntExact(Long.valueOf(opportunityUpdated.getId())));
		opportunity.setName(opportunityUpdated.getName());
		opportunity.setDate(opportunityUpdated.getDate());

		opportunityRepo.save(opportunity);

		return "edit_success";
	}

	@RequestMapping("/delete_opportunity/{id}")
	public String deleteOpportunity(@PathVariable(name = "id") int id) {
		opportunityRepo.deleteById(Math.toIntExact(Long.valueOf(id)));
		return "delete_success";
	}

	//list all activities
	@GetMapping("/list_activity")
	public String showActivities(Model model) {
		List<Activity> activityList = activityRepo.findAll();
		model.addAttribute("list", activityList);
		return "list_activity";
	}

	//create a new activity
	@GetMapping("/new_activity")
	public String showCreateActivity(Model model) {
		model.addAttribute("activity", new Activity());
		return "new_activity";
	}

	//new activity created successfully
	@PostMapping("/process_activity")
	public String createActivity(Activity activity) {
		activityRepo.save(activity);
		return "activity_create_success";
	}

}
