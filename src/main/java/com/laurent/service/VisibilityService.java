package com.laurent.service;

import com.laurent.model.Customer;
import com.laurent.model.Visibility;
import com.laurent.service.visibility.factory.VisibilityStrategyFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class VisibilityService {
    private final EntityManager entityManager;
    private final VisibilityStrategyFactoryService visibilityStrategyFactoryService;

    @Autowired
    public VisibilityService(EntityManager entityManager,
                             VisibilityStrategyFactoryService visibilityStrategyFactoryService) {
        this.entityManager = entityManager;
        this.visibilityStrategyFactoryService = visibilityStrategyFactoryService;
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

    public boolean isVisible(Customer customer) {
        return visibilityStrategyFactoryService.createVisibilityStrategyFactory(customer.getVisibility())
                .isVisible(customer);
    }
}
