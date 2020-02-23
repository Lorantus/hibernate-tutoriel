package com.laurent.infra;

import com.laurent.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    List<Customer> findByLastName(String lastName);
}