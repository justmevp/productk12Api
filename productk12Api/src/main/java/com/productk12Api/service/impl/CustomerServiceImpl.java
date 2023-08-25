package com.productk12Api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productk12Api.base.BaseResponse;
import com.productk12Api.model.Customer;
import com.productk12Api.model.CustomerDTO;
import com.productk12Api.repository.CustomerRepository;
import com.productk12Api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends BaseResponse implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public ResponseEntity<?> getListCustomer() {
        return getResponseEntity(customerRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getListCustomerPaging(int page,int size,String propSortName) {
        Pageable pageable = PageRequest.of(page,size,
                Sort.by(Sort.Direction.ASC,"cusname"));
        Page<Customer> pageCus = customerRepository.findAll(pageable);
        return getResponseEntity(pageCus);
    }

    @Override
    public ResponseEntity<?> save(Customer customer) {
        try {
            Customer savedCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>("Customer deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<?> findById(int id) {
        try {
            Customer customer = customerRepository.findById(id).orElse(null);
            if (customer == null) {
                return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getListPurchased() {
        List<Map<String,Object>> maps = customerRepository.getListPurchasedMap();
        ObjectMapper objectMapper = new ObjectMapper();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Map m: maps) {
            CustomerDTO customerDTO = objectMapper.convertValue(m, CustomerDTO.class);
            customerDTOList.add(customerDTO);
        }
        System.out.println(customerDTOList.get(0).getBirthday());
        Date date = customerDTOList.get(0).getBirthday();
        return getResponseEntity(customerDTOList);
    }
}
