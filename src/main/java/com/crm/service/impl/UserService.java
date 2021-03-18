package com.crm.service.impl;

import com.crm.model.Opportunity;
import com.crm.model.User;
import com.crm.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {


    private static final Logger logger1 = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private LeadRepository repo;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OpportunityRepository opportunityRepo;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public List<User> listAll() {

        logger1.info("Get All User success!");
        return userRepository.findAll();
    }

    public void save(User user) {

        logger1.info("User Save successfully!");
        userRepository.save(user);
    }

    public User get(int id) {
        logger1.info("Get User By Id success!");

        return userRepository.findById((long) id).get();
    }


    public void delete(int id) {
        userRepository.deleteById((long) id);
        logger1.info("User Deleted successfully!");
    }



    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}


