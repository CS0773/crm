package com.crm.service;

import com.crm.model.Member;
import com.crm.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
