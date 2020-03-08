package com.laurent.infra;

import com.laurent.VisibiltityTestConfig;
import com.laurent.command.VisibilityByLocaleCommandService;
import com.laurent.command.VisibilityByNameCommandService;
import com.laurent.model.Customer;
import com.laurent.model.Visibility;
import com.laurent.model.VisibilityType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.util.collections.Sets.newSet;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
@Import(VisibiltityTestConfig.class)
public class VisibilityRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VisibilityRepository visibilityRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VisibilityByLocaleCommandService visibilityRepositoryByLocaleCommandService;

    @Autowired
    private VisibilityByNameCommandService visibilityByNameCommandService;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("John", "doe");
        customer.setLocale(Locale.FRANCE);
        entityManager.persist(customer);

        visibilityRepositoryByLocaleCommandService.create(customer, newSet(Locale.FRANCE, Locale.UK))
                .execute();
    }

    @Test
    public void testFindByCustomer() {
        List<Visibility> visibilities = visibilityRepository.findByCustomer(customer);

        assertThat(visibilities).hasSize(1)
                .extracting(Visibility::getVisibilityType)
                .containsOnly(VisibilityType.LOCALE);
    }

    @Test
    public void testVisibilityByLocale() {
        List<Visibility> visibilities = visibilityRepository.findByCustomer(customer);

        assertThat(visibilities).hasSize(1)
                .first()
                .extracting(Visibility::isVisible)
                .isEqualTo(true);
    }

    @Test
    public void testNoVisibilityByLocale() {
        customer.setLocale(Locale.GERMAN);
        entityManager.persist(customer);

        List<Visibility> visibilities = visibilityRepository.findByCustomer(customer);

        assertThat(visibilities).hasSize(1)
                .first()
                .extracting(Visibility::isVisible)
                .isEqualTo(false);
    }

    @Test
    public void testVisibilityByLocales() {
        visibilityByNameCommandService.create(customer, newSet("john"))
                .execute();

        List<Visibility> visibilities = visibilityRepository.findByCustomer(customer);

        assertThat(visibilities).hasSize(1)
                .extracting(Visibility::getVisibilityType)
                .containsExactly(VisibilityType.NAME);
    }
}