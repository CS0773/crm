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
	private OpportunityRepository opportunityRepo;

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private ActivityRepository activityRepo;

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




	@GetMapping("/user_list")
	public String listUsersAfterLogin(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "user_list";
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

	@GetMapping("/opportunity_list")
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

		return "opportunity_update_success";
	}

	@RequestMapping("/delete_opportunity/{id}")
	public String deleteOpportunity(@PathVariable(name = "id") int id) {
		opportunityRepo.deleteById(Math.toIntExact(Long.valueOf(id)));
		return "delete_success";
	}

	@GetMapping("/member_list")
	public String listMember(Model model) {
		List<Member> listMember = memberRepo.findAll();
		model.addAttribute("listMember", listMember);
		return "member_list";
	}



	@GetMapping("/create_member")
	public String showMemberForm(Model model) {
		model.addAttribute("member", new Member());

		return "create_member";
	}
	@PostMapping("/process_member")
	public String saveMember(Member member) {


		memberRepo.save(member);

		return "member_succes";
	}


	@RequestMapping("/load_edit_member/{id}")
	public ModelAndView showEditMemberPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_member");
		 Member member = memberRepo.getOne(Math.toIntExact(Long.valueOf(id)));;
		mav.addObject("member", member);

		return mav;
	}

	@PostMapping("/edit_member")
	public String showEditMemberPage(@ModelAttribute("member") Member memberUpdated) {


		Member member = memberRepo.getOne(Math.toIntExact(Long.valueOf(memberUpdated.getId())));
		member.setAccname(memberUpdated.getAccname());
		member.setAccno(memberUpdated.getAccno());

		memberRepo.save(member);

		return "member_update_success";
	}

	@RequestMapping("/delete_member/{id}")
	public String deleteMember(@PathVariable(name = "id") int id) {
		memberRepo.deleteById(Math.toIntExact(Long.valueOf(id)));
		return "delete_success";
	}

	//list all activities
	@GetMapping("/activity_list")
	public String showActivities(Model model) {
		List<Activity> activityList = activityRepo.findAll();
		model.addAttribute("list", activityList);
		return "activity_list";
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
	@RequestMapping("/edit_activity/{id}")
	public ModelAndView showUpdateActivity(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("edit_activity");
		Activity activity=activityRepo.getOne(id);
		mav.addObject("activity",activity);
		return mav;
	}

	@PostMapping("/process_edit_activity")
	public String updateActivity(Activity receivedActivity) {
		Activity activity = activityRepo.getOne(receivedActivity.getId());
		activity.setActivityType(receivedActivity.getActivityType());
		activity.setAccountName(receivedActivity.getLeadAccountName());
		activity.setAssignedTo(receivedActivity.getAssignedTo());
		activity.setDueDate(receivedActivity.getDueDate());
		activity.setComments(receivedActivity.getComments());
		activity.setStatus(receivedActivity.getStatus());
		activityRepo.save(activity);
		return "redirect:/activity_list";
	}

	@RequestMapping("/delete_activity/{id}")
	public String deleteActivity(@PathVariable Integer id) {
		activityRepo.deleteById(id);
		return "redirect:/activity_list";
	}



}
