package com.laurent.service;

import com.laurent.model.Customer;
import com.laurent.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class VisibilityService {
    private final EntityManager entityManager;

    @Autowired
    public VisibilityService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void updateVisibility(Customer customer, Visibility visibility) {
        Visibility customerVisibility = customer.getVisibility();
        if(customerVisibility != null) {
            customer.setVisibility(null);
            entityManager.remove(customerVisibility);
            entityManager.flush();
        }
        customer.setVisibility(visibility);
    }
}
