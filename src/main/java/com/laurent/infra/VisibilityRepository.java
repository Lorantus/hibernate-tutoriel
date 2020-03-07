package com.laurent.infra;

import com.laurent.model.Customer;
import com.laurent.model.Visibility;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VisibilityRepository extends CrudRepository<Visibility, UUID> {
    List<Visibility> findByCustomer(Customer customer);
}