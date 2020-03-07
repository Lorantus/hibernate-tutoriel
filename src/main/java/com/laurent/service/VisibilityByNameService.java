package com.laurent.service;

import com.laurent.infra.CustomerRepository;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisibilityByNameService {
    private final CustomerRepository customerRepository;

    @Autowired
    public VisibilityByNameService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void associe(Customer customer, Set<String> names) {
        customer.setVisibility(new VisibilityByName(names));
        customerRepository.save(customer);
    }
}
