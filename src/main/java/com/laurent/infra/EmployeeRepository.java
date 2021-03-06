package com.laurent.infra;

import com.laurent.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    List<Employee> findByCompany(String company);
}
