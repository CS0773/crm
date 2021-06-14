package com.crm.service.impl;

import com.crm.model.*;
import com.crm.service.LeadRepository;
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
public class LeadService {

	private static final Logger logger = LoggerFactory.getLogger(LeadService.class);

	@Autowired
	private LeadRepository repo;



	@Autowired
	private ProductRepository productRepository;


	public List<Leads> listAll() {

		logger.debug("Get All Lead success!");
		return repo.findAll();
	}
	
	public void save(Leads leads) {

		logger.debug("Lead Save successfully!");
		repo.save(leads);
	}
	
	public Leads get(int id) {
		logger.debug("Get Lead By Id success!");

		return repo.findById(id).get();
	}

	public List<Product> getAllProduct() {
		logger.debug("Lead List successfully!");
		return productRepository.findAll();
	}
	public void delete(int id) {
		repo.deleteById(id);
		logger.debug("Lead Deleted successfully!");
	}

	@Transactional
	public void convertLead(int id) {

		// STEP 1 Updating Lead status to CONVERTED
		Leads leads = new Leads();
		leads = repo.findById(id).get();

		leads.setStatus(LeadStatus.CONVERTED);


		// STEP 3 Create new Member


		repo.save(leads);

		logger.debug("Lead Convert successfully!");

	}


	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
