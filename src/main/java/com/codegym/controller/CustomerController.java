package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping({"", "/customers"})
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customer/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showListCustomers() {
        ModelAndView modelAndView = new ModelAndView("customer/add");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showInformation(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/info");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    //    @PostMapping()
    //    public String updateCustomer(Long id, String name, String email, String address) {
    //        Customer customer = new Customer(id, name, email, address);
    //        customerService.save(customer);
    //        return "redirect:/customers";
    //    }

    @PostMapping()
    public String save(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
}
