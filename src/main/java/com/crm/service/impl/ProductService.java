package com.crm.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.crm.model.Product;
import com.crm.service.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(LeadService.class);

    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        logger.debug("Get All products success!");
        return repo.findAll();
    }

    public void save(Product product) {
        logger.debug("Product Saved successfully!");
        repo.save(product);
    }

    public Product get(Integer id) {
        logger.debug("Get Product By Id success!");
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        logger.debug("Product Deleted successfully!");
        repo.deleteById(id);
    }
}