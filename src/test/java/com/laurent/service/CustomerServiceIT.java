package com.laurent.service;

import com.laurent.VisibiltityTestConfig;
import com.laurent.infra.CustomerRepository;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityByLocale;
import com.laurent.model.VisibilityByName;
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
@DataJpaTest(showSql = true)
@Import(VisibiltityTestConfig.class)
public class CustomerServiceIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void doitMettreAJourLaVisibiliteCustomer() {
        // GIVEN
        Customer customer = new Customer("John", "doe");
        customer.setVisibility(new VisibilityByLocale(newSet(Locale.FRANCE, Locale.UK)));
        customerRepository.save(customer);

        // WHEN
        customerService.updateVisibility(customer, new VisibilityByName(newSet("john")));

        // THEN
        assertThat(customerRepository.findById(customer.getId())).get()
                .extracting(Customer::getVisibility)
                .isInstanceOf(VisibilityByName.class)
                .extracting(visibility -> new ArrayList<>(((VisibilityByName)visibility).getNames())).asList()
                .containsExactly("john");
    }
}