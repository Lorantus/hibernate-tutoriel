package com.laurent.infra;

import com.laurent.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.internal.util.collections.Sets.newSet;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class CustomerRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByName() {
        Customer customer = new Customer("John", "doe");
        customer.setVisibility(new VisibilityByLocale(newSet(Locale.FRANCE, Locale.UK)));
        entityManager.persist(customer);

        List<Customer> customers = customerRepository.findByLastName("doe");

        assertThat(customers).hasSize(1)
                .extracting(Customer::getName, Customer::getLastName)
                .containsOnly(tuple("John", "doe"));
    }

    @Test
    public void testKeepVisiblityByLocale() {
        Customer customer = new Customer("John", "doe");
        customer.setVisibility(new VisibilityByLocale(newSet(Locale.FRANCE, Locale.UK)));
        entityManager.persist(customer);

        List<Customer> customers = customerRepository.findByLastName("doe");

        assertThat(customers).hasSize(1)
                .extracting(Customer::getVisibility)
                .filteredOn(o -> o instanceof VisibilityByLocale)
                .flatExtracting(visibility -> ((VisibilityByLocale)visibility).getLocales())
                .containsOnly(Locale.FRANCE, Locale.UK);
    }

    @Test
    public void testKeepVisiblityByName() {
        Customer customer = new Customer("John", "doe");
        customer.setVisibility(new VisibilityByName(newSet("John", "Jane")));
        entityManager.persist(customer);

        List<Customer> customers = customerRepository.findByLastName("doe");

        assertThat(customers).hasSize(1)
                .extracting(Customer::getVisibility)
                .filteredOn(o -> o instanceof VisibilityByName)
                .flatExtracting(visibility -> ((VisibilityByName)visibility).getNames())
                .containsOnly("John", "Jane");
    }

    @Test
    public void testGetOrders() {
        Customer customer = new Customer("John", "doe");
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