package com.crm.service;

import com.crm.model.Leads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Leads, Integer> {
}
