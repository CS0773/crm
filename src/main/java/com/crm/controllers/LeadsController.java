package com.crm.controllers;

import com.crm.model.Activity;
import com.crm.model.LeadStatus;
import com.crm.model.Leads;
import com.crm.model.Product;
import com.crm.service.LeadRepository;
import com.crm.service.impl.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Column;
import java.util.List;

@Controller
public class LeadsController {

    @Autowired
    private LeadService leadService;

    //	listing lead
    @GetMapping("/lead_list")
    public String listLeads(Model model) {
        List<Leads> listLeads = leadService.listAll();
        model.addAttribute("listLeads", listLeads);
        return "lead_list";
    }

    //  lead creation
    @RequestMapping("/new_lead")
    public String showNewLeadsPage(Model model) {
        Leads leads = new Leads();
        model.addAttribute("leads", leads);
        return "new_lead";
    }

    @RequestMapping(value = "/save_lead", method = RequestMethod.POST)
    public String saveLead(@ModelAttribute("leads") Leads leads) {
        leadService.save(leads);
        return "redirect:/lead_list";
    }


    @RequestMapping("/load_edit_lead/{id}")
    public ModelAndView showEditLeadPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_lead");
        Leads leads = leadService.get(id);
        mav.addObject("leads", leads);
        return mav;
    }

    @PostMapping("/process_edit_lead")
    public String updateLead(Leads updatedLead) {
        Leads existingLead = leadService.get(updatedLead.getId());
        existingLead.setFirstName(updatedLead.getFirstName());
        existingLead.setLastName(updatedLead.getLastName());
        existingLead.setCompany(updatedLead.getCompany());
        existingLead.setEmailId(updatedLead.getEmailId());
        existingLead.setPhoneNumber(updatedLead.getPhoneNumber());
        existingLead.setStatus(updatedLead.getStatus());
        existingLead.setProductList(updatedLead.getProductList());
        leadService.save(existingLead);
        return "redirect:/lead_list";
    }

    @RequestMapping("/delete_lead/{id}")
    public String deleteLead(@PathVariable(name = "id") int id) {
        leadService.delete(id);
        return "redirect:/lead_list";
    }

    @RequestMapping("/convert_lead/{id}")
    public String convertLead(@PathVariable(name = "id") int id) {
        leadService.convertLead(id);
        return "redirect:/lead_list";
    }
}
