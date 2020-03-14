package com.laurent.service;

import com.laurent.model.Customer;
import com.laurent.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CustomerService {
    private final EntityManager entityManager;

    @Autowired
    public CustomerService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void updateVisibility(Customer customer, Visibility visibility) {
        Visibility customerVisibility = customer.getVisibility();
        customer.setVisibility(null);
        entityManager.remove(customerVisibility);
        entityManager.flush();

        customer.setVisibility(visibility);
    }
}
