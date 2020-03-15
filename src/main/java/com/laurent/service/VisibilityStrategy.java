package com.laurent.service;

import com.laurent.model.Customer;

public interface VisibilityStrategy {
    boolean isVisible(Customer customer);
}
