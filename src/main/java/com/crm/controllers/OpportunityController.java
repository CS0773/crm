package com.crm.controllers;

import com.crm.model.Leads;
import com.crm.model.Opportunity;
import com.crm.service.OpportunityRepository;
import com.crm.service.impl.LeadService;
import com.crm.service.impl.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OpportunityController {
    @Autowired
    private OpportunityService opportunityService;

    @Autowired
    private OpportunityRepository opportunityRepo;


    @GetMapping("/opportunity_page")
    public String showOpportunityForm(Model model) {
        model.addAttribute("opportunity", new Opportunity());

        return "opportunity";
    }
    @PostMapping("/process_opportunity")
    public String saveOpportunity(Opportunity opportunity) {


       // opportunityRepo.save(opportunity);
        opportunityService.save(opportunity);

        return "opportunity_success";
    }

    @GetMapping("/opportunity_list")
    public String opportunityListUsers(Model model) {
        List<Opportunity> listUsers = opportunityRepo.findAll();
        List<Opportunity> listOpportunity = opportunityService.listAll();
        model.addAttribute("listUsers", listUsers);

        return "opportunity_list";
    }

    @RequestMapping("/load_edit_opportunity/{id}")
    public ModelAndView showEditOpportunityPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_opportunity");
        Opportunity opportunity1=opportunityService.get(id);
        Opportunity opportunity = opportunityRepo.getOne(Math.toIntExact(Long.valueOf(id)));;
        mav.addObject("opportunity", opportunity1);

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
       // opportunityRepo.deleteById(Math.toIntExact(Long.valueOf(id)));
        opportunityService.delete(id);
        return "delete_success";
    }

}
