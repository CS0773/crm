package com.crm.controllers;

import com.crm.model.Leads;
import com.crm.model.Product;
import com.crm.service.LeadRepository;
import com.crm.service.impl.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LeadsController {

//    private JavaConsole javaConsole = new JavaConsole();
    @Autowired
    private LeadService leadService;
    @Autowired
    private LeadRepository leadRepo;
    //	listing lead
    @GetMapping("/lead_list")
    public String listLeads(Model model) {
        List<Leads> listLeads = leadService.listAll();
        model.addAttribute("listLeads", listLeads);

//        javaConsole.logForEvent(LeadsController.class,"listLeads", "LIST LEAD success !");

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

//        javaConsole.logForEvent(LeadsController.class,"saveLead", "CREATE LEAD success !");

        return "redirect:/lead_list";
    }


    @RequestMapping("/load_edit_lead/{id}")
    public ModelAndView showEditLeadPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_lead");
        Leads leads = leadService.get(id);
        List<Product> product= leadService.getAllProduct();
        mav.addObject("leads", leads);
        mav.addObject("productList",product);
        return mav;
    }

    @PostMapping("/process_edit_lead")
        public String editLeadsPage(@ModelAttribute("leads") Leads leads) {
//        System.out.println("successs");
            Leads leads1 = leadRepo.getOne(Math.toIntExact(Long.valueOf(leads.getId())));
            leads1.setFirstName(leads.getFirstName());
            leads1.setLastName(leads.getLastName());
            leads1.setCompany(leads.getCompany());
            leads1.setEmailId(leads.getEmailId());
            leads1.setPhoneNumber(leads.getPhoneNumber());
            leads1.setStatus(leads.getStatus());
            leadRepo.save(leads1);
             leadService.getByName(leads);
            return "edit_lead_success";
        }


    @RequestMapping("/delete_lead/{id}")
    public String deleteLead(@PathVariable(name = "id") int id) {
        leadRepo.deleteById(Math.toIntExact(Long.valueOf(id)));
        return "delete_success";
    }

    @RequestMapping("/convert_lead/{id}")
    public String convertLead(@PathVariable(name = "id") int id) {
        leadService.convertLead(id);
        return "convert_success";
    }
}
