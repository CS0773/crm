package com.crm.service.impl;


import com.crm.model.Leads;
import com.crm.model.Member;
import com.crm.model.Opportunity;
import com.crm.service.LeadRepository;
import com.crm.service.MemberRepository;
import com.crm.service.OpportunityRepository;
import com.crm.service.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
    @Autowired
    private LeadRepository repo;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OpportunityRepository opportunityRepo;

    @Autowired
    private ProductRepository productRepository;
    public List<Member> listAll() {

        logger.info("Get All Member success!");
        return memberRepository.findAll();
    }
    public void save(Member member) {

        logger.info("Member Save successfully!");
        memberRepository.save(member);
    }
    public void delete(int id) {
        memberRepository.deleteById(id);
        logger.info("member Deleted successfully!");
    }
    public Member get(int id) {
        logger.info("Get Member By Id success!");

        return memberRepository.findById(id).get();
    }



}
