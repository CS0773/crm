package com.crm.service.impl;

import com.crm.model.Activity;
import com.crm.model.Leads;
import com.crm.model.User;
import com.crm.service.ActivityRepository;
import com.crm.service.LeadRepository;
import com.crm.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(LeadService.class);

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private LeadRepository leadRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Activity> listAll() {
        logger.debug("Get All activity success!");
        return activityRepo.findAll();
    }

    public void save(Activity activity) {
        logger.debug("Activity Saved successfully!");
        activityRepo.save(activity);
    }

    public Activity get(int id) {
        logger.debug("Get Activity By Id success!");
        return activityRepo.findById(id).get();
    }

    public void delete(int id) {
        logger.debug("Activity Deleted successfully!");
        activityRepo.deleteById(id);
    }

    public List<Leads> getAllLeads() {
        logger.debug("Activity-Lead List successfully!");
        return leadRepo.findAll();
    }
    public List<User> getAllUsers() {
        logger.debug("Activity-User List successfully!");
        return userRepo.findAll();
    }
}
