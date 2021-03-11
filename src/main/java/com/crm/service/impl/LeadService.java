package com.crm.service.impl;

import com.crm.model.LeadStatus;
import com.crm.model.Leads;
import com.crm.service.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeadService {

	@Autowired
	private LeadRepository repo;
	
	public List<Leads> listAll() {
		return repo.findAll();
	}
	
	public void save(Leads leads) {
		repo.save(leads);
	}
	
	public Leads get(int id) {
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Transactional
	public void convertLead(int id) {
		Leads leads = new Leads();
		leads = repo.findById(id).get();

		leads.setStatus(LeadStatus.CONVERTED);
		repo.save(leads);

	}
}
