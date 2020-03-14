package com.laurent.service;

import com.laurent.infra.CustomerRepository;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityByLocale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

@Service
public class VisibilityByLocaleService {
    private final CustomerRepository customerRepository;

    @Autowired
    public VisibilityByLocaleService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void associer(Customer customer, Set<Locale> locales) {
        customer.setVisibility(new VisibilityByLocale(locales));
        customerRepository.save(customer);
    }
}
