package com.laurent.command.factory;

import com.laurent.VisibiltityTestConfig;
import com.laurent.command.VisibilityByLocaleCommandService;
import com.laurent.command.VisibilityByNameCommandService;
import com.laurent.command.VisibiltyCommand;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityType;
import com.laurent.service.VisibilityByLocaleService;
import com.laurent.service.VisibilityByNameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.util.collections.Sets.newSet;

@RunWith(SpringRunner.class)
@Import(VisibiltityTestConfig.class)
public class VisibilityCommandServiceFactoryTest {
    @MockBean
    private VisibilityByLocaleService visibilityByLocaleService;

    @MockBean
    private VisibilityByNameService visibilityByNameService;

    @Autowired
    private VisibilityCommandServiceFactory visibilityFactoryService;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("john", "doe");
    }

    @Test
    public void doitRetournerUneCommandePourLocale() {
        // WHEN
        VisibiltyCommand visibilityCommand = visibilityFactoryService.createVisibilityCommand(customer, VisibilityType.LOCALE, newSet("fr", "en"));

        // THEN
        assertThat(visibilityCommand)
                .isInstanceOf(VisibilityByLocaleCommandService.VisibilityByLocaleCommand.class);

        assertThat(((VisibilityByLocaleCommandService.VisibilityByLocaleCommand)visibilityCommand).getLocales())
                .containsOnly(Locale.FRENCH, Locale.ENGLISH);
    }

    @Test
    public void doitRetournerUneCommandePourName() {
        // WHEN
        VisibiltyCommand visibilityCommand = visibilityFactoryService.createVisibilityCommand(customer, VisibilityType.NAME, newSet("John", "Jane"));

        // THEN
        assertThat(visibilityCommand)
                .isInstanceOf(VisibilityByNameCommandService.VisibilityByNameCommand.class);

        assertThat(((VisibilityByNameCommandService.VisibilityByNameCommand)visibilityCommand).getNames())
                .containsOnly("John", "Jane");
    }
}