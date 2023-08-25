package com.productk12Api.controller;

import com.productk12Api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return customerService.getListCustomer();
    }
    @GetMapping("/getListCustomerPaging")
    public ResponseEntity<?> getListCustomerPaging(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String sort) {
        return customerService.getListCustomerPaging(page, size, sort);
    }

}
