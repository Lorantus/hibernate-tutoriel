package com.laurent.infra;

import com.laurent.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class EmployeeRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindByCompany() {
        Employee employee = new Employee();
        employee.setName("Laurent");
        employee.setCompany("Bratislava");
        entityManager.persist(employee);

        List<Employee> employees = employeeRepository.findByCompany("Bratislava");

        assertThat(employees).hasSize(1)
                .extracting(Employee::getName, Employee::getCompany)
                .containsOnly(tuple("Laurent", "Bratislava"));
    }
}