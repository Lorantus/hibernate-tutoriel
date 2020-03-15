package com.laurent.service.visibility;

import com.laurent.model.Customer;
import com.laurent.service.VisibilityStrategy;

public class VisibilityByNoneStrategy implements VisibilityStrategy {
    @Override
    public boolean isVisible(Customer customer) {
        return false;
    }
}
