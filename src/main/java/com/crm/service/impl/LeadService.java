package com.crm.service.impl;

import com.crm.model.*;
import com.crm.service.LeadRepository;
import com.crm.service.MemberRepository;
import com.crm.service.OpportunityRepository;
import com.crm.service.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LeadService {

	@Autowired
	private LeadRepository repo;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private OpportunityRepository opportunityRepo;

	@Autowired
	private ProductRepository productRepository;


	public List<Leads> listAll() {
		return repo.findAll();
	}
	
	public void save(Leads leads) {
		repo.save(leads);
	}
	
	public Leads get(int id) {
		return repo.findById(id).get();
	}
	public List<Product> getAllProduct() {
		return (productRepository.findAll());
	}
	@Transactional
	public void getByName(Leads leads) {
//		System.out.println("addingg");
		List<Product> addList = new ArrayList<>();
		for (Product p:leads.getProductList()) {
			addList.add(productRepository.findByName(p.getName()));
//			addList.forEach(s-> System.out.println(s.getName()));
		}
		Leads leads1=repo.getOne(leads.getId());
		leads1.setProductList(addList);
		repo.save(leads1);
	}
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Transactional
	public void convertLead(int id) {

		// STEP 1 Updating Lead status to CONVERTED
		Leads leads = new Leads();
		leads = repo.findById(id).get();

		leads.setStatus(LeadStatus.CONVERTED);


		// STEP 3 Create new Member
		Member member = new Member();
		member.setAccname(leads.getFirstName() + " " + leads.getLastName() + " - CONVERTED "+getRandomNumber(1,100));
		member.setAccno(getRandomNumber(1,100000));
//		member.setLead(leads);
		memberRepository.save(member);

		// STEP 2 Create new Opportunity
		Opportunity opportunity = new Opportunity();
		opportunity.setName(leads.getFirstName() + " " + leads.getLastName() + " - CONVERTED "+getRandomNumber(1,100));
		opportunity.setDate(new Date().toString());
//		opportunity.setMember(member);
		opportunityRepo.save(opportunity);

		repo.save(leads);

	}


	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
