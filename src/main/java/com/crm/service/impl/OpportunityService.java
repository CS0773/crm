package com.crm.service.impl;

import com.crm.model.*;
import com.crm.service.LeadRepository;
import com.crm.service.MemberRepository;
import com.crm.service.OpportunityRepository;
import com.crm.service.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OpportunityService {

    private static final Logger logger1 = LoggerFactory.getLogger(OpportunityService.class);

    @Autowired
    private LeadRepository repo;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OpportunityRepository opportunityRepo;

    @Autowired
    private ProductRepository productRepository;


    public List<Opportunity> listAll() {

        logger1.info("Get All Opportunity success!");
        return opportunityRepo.findAll();
    }

    public void save(Opportunity opportunity) {

        logger1.info("Opportunity Save successfully!");
        opportunityRepo.save(opportunity);
    }

    public Opportunity get(int id) {
        logger1.info("Get Opportunity By Id success!");

        return opportunityRepo.findById(id).get();
    }


    public void delete(int id) {
        opportunityRepo.deleteById(id);
        logger1.info("Opportunity Deleted successfully!");
    }



    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
