package com.crm.controllers;

import com.crm.model.Leads;
import com.crm.model.Member;
import com.crm.model.Opportunity;
import com.crm.service.MemberRepository;
import com.crm.service.impl.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepo;
    @GetMapping("/member_list")
    public String listMember(Model model) {
       //List<Member> listMember = memberRepo.findAll();
        List<Member> listMember = memberService.listAll();
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
        memberService.save(member);
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
        //memberRepo.deleteById(Math.toIntExact(Long.valueOf(id)));
        memberService.delete(id);
        return "delete_success";
    }


}
