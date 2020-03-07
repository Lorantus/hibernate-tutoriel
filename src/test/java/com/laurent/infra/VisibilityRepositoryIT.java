package com.laurent.infra;

import com.laurent.command.VisibilityType;
import com.laurent.model.Customer;
import com.laurent.model.Visibility;
import com.laurent.model.VisibilityByLocale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.util.collections.Sets.newSet;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class VisibilityRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VisibilityRepository visibilityRepository;

    @Test
    public void testFindByCustomer() {
        Customer customer = new Customer("John", "doe");
        customer.setVisibility(new VisibilityByLocale(newSet(Locale.FRANCE, Locale.UK)));
        entityManager.persist(customer);

        List<Visibility> visibilities = visibilityRepository.findByCustomer(customer);

        assertThat(visibilities).hasSize(1)
                .extracting(Visibility::getVisibilityType)
                .containsOnly(VisibilityType.LOCALE);
    }
}