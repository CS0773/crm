package com.crm.controllers;

import com.crm.model.Activity;
import com.crm.model.Leads;
import com.crm.model.User;
import com.crm.service.ActivityRepository;
import com.crm.service.impl.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    //list all activities
    @GetMapping("/activity_list")
    public String showActivities(Model model) {
        List<Activity> activityList = activityService.listAll();
        model.addAttribute("list", activityList);
        return "activity_list";
    }

    //create a new activity
    @GetMapping("/new_activity")
    public String showCreateActivity(Model model) {
        model.addAttribute("activity", new Activity());
        List<Leads> leads = activityService.getAllLeads();
        model.addAttribute("leads", leads);
        List<User> users = activityService.getAllUsers();
        model.addAttribute("users",users);
        return "new_activity";
    }

    //new activity created successfully
    @PostMapping("/process_activity")
    public String createActivity(Activity activity) {
        activityService.save(activity);
        return "activity_create_success";
    }
    @RequestMapping("/edit_activity/{id}")
    public ModelAndView showUpdateActivity(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("edit_activity");
        Activity activity=activityService.get(id);
        mav.addObject("activity",activity);
        List<Leads> leads = activityService.getAllLeads();
        mav.addObject("leads", leads);
        List<User> users = activityService.getAllUsers();
        mav.addObject("users",users);
        return mav;
    }

    @PostMapping("/process_edit_activity")
    public String updateActivity(Activity receivedActivity) {
        Activity activity = activityService.get(receivedActivity.getId());
        activity.setActivityType(receivedActivity.getActivityType());
        activity.setLeadAccountName(receivedActivity.getLeadAccountName());
        activity.setAssignedTo(receivedActivity.getAssignedTo());
        activity.setDueDate(receivedActivity.getDueDate());
        activity.setComments(receivedActivity.getComments());
        activity.setStatus(receivedActivity.getStatus());
        activityService.save(activity);
        return "redirect:/activity_list";
    }

    @RequestMapping("/delete_activity/{id}")
    public String deleteActivity(@PathVariable Integer id) {
        activityService.delete(id);
        return "redirect:/activity_list";
    }
}
