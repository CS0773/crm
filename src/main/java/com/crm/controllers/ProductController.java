package com.crm.controllers;

import com.crm.model.Product;
import com.crm.model.User;
import com.crm.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    //1 RESTful API methods for Retrieval operations - ALL
    @RequestMapping("/product_list")
    public String viewProductsHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "product_list";
    }

    //2 RESTful API methods for Retrieval operations - By Id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        try {
            Product product = service.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/new_product")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    //3 RESTful API method for Create operation
    //new product created successfully
    @PostMapping("/save_product")
    public String add(Product product) {
        service.save(product);
        return "product_create_success";
    }

    @RequestMapping("/edit_product/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);

        return mav;
    }

    //4 RESTful API method for Update operation
/*    @PostMapping("/process_edit_product")
    public ResponseEntity<?> update(@RequestBody Product productUpdates) {
        try {
            Product existProduct = service.get(productUpdates.getId());
            existProduct.setName(productUpdates.getName());
            existProduct.setPrice(productUpdates.getPrice());

            service.save(existProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @PostMapping("/process_edit_product")
    public String updateProduct(@ModelAttribute("product") Product productUpdates) {
        Product existProduct = service.get(productUpdates.getId());
        existProduct.setName(productUpdates.getName());
        existProduct.setPrice(productUpdates.getPrice());

        service.save(existProduct);

        return "edit_success";
    }


    //5 RESTful API method for Delete operation
    @RequestMapping("/delete_product/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/product_list";
    }
}