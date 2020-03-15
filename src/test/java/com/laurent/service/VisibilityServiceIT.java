package com.laurent.service;

import com.laurent.VisibiltityTestConfig;
import com.laurent.infra.CustomerRepository;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityByLocale;
import com.laurent.model.VisibilityByName;
import com.laurent.model.VisibilityByNone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.util.collections.Sets.newSet;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
@Import(VisibiltityTestConfig.class)
public class VisibilityServiceIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VisibilityService visibilityService;

    @Test
    public void doitMettreAJourLaVisibiliteCustomer() {
        // GIVEN
        Customer customer = new Customer("John", "doe");
        customer.setVisibility(new VisibilityByLocale(newSet(Locale.FRANCE, Locale.UK)));
        customerRepository.save(customer);

        // WHEN
        visibilityService.updateVisibility(customer, new VisibilityByName(newSet("john")));

        // THEN
        assertThat(customerRepository.findById(customer.getId())).get()
                .extracting(Customer::getVisibility)
                .isInstanceOf(VisibilityByName.class)
                .extracting(visibility -> new ArrayList<>(((VisibilityByName)visibility).getNames())).asList()
                .containsExactly("john");
    }

    @Test
    public void doitMettreAJourLaVisibiliteCustomerANone() {
        // GIVEN
        Customer customer = new Customer("John", "doe");
        customer.setVisibility(new VisibilityByLocale(newSet(Locale.FRANCE, Locale.UK)));
        customerRepository.save(customer);

        // WHEN
        visibilityService.updateVisibility(customer, new VisibilityByNone());

        // THEN
        assertThat(customerRepository.findById(customer.getId())).get()
                .extracting(Customer::getVisibility)
                .isInstanceOf(VisibilityByNone.class);
    }

    @Test
    public void doitComparerLaVisibiliteCustomer() {
        // GIVEN
        Customer customer = new Customer("John", "doe");
        customer.setLocale(Locale.FRANCE);
        customer.setVisibility(new VisibilityByLocale(newSet(Locale.FRANCE, Locale.UK)));
        customerRepository.save(customer);

        // WHEN
        boolean visible = visibilityService.isVisible(customer);

        // THEN
        assertThat(visible).isTrue();
    }
}