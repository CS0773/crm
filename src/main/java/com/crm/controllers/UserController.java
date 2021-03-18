package com.crm.controllers;

import com.crm.model.Opportunity;
import com.crm.model.User;
import com.crm.service.UserRepository;
import com.crm.service.impl.UserService;
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
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;


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
        List<User> listOpportunity = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        return "user_list";
    }


    @RequestMapping("/load_edit_user/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userRepo.getOne(Long.valueOf(id));;
        User user1=userService.get(id);
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

        userService.save(user);

        //userRepo.save(user);

        return "edit_success";
    }

    @RequestMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
       // userRepo.deleteById(Long.valueOf(id));
        userService.delete(id);
        return "delete_success";
    }

}
