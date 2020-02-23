package com.laurent.infra;

import com.laurent.model.Customer;
import com.laurent.model.Order;
import com.laurent.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class CustomerRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByName() {
        entityManager.persist(new Customer("4320", "Bratislava"));

        List<Customer> customers = customerRepository.findByLastName("Bratislava");

        assertThat(customers).hasSize(1)
                .extracting(Customer::getFirstName, Customer::getLastName)
                .containsOnly(tuple("4320", "Bratislava"));
    }

    @Test
    public void testGetOrders() {
        Customer customer = new Customer("Bratislava", "City");
        UUID customerId = entityManager.persist(customer).getId();

        Product product = new Product("Bratislava", new BigDecimal("100"));
        product = entityManager.persist(product);

        Order order = new Order("numero", customer);
        order.getProducts().add(product);
        entityManager.persist(order);

        customer.getOrders().add(order);

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        assertThat(customerOptional)
                .hasValueSatisfying(customerFound ->
                        assertThat(customerFound.getOrders()).hasSize(1)
                                .extracting(Order::getNumero)
                                .containsOnly("numero"));
    }
}