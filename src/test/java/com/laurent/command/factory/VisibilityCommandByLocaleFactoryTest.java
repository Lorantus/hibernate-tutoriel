package com.laurent.command.factory;

import com.laurent.VisibiltityTestConfig;
import com.laurent.command.VisibilityByLocaleCommandService.VisibilityByLocaleCommand;
import com.laurent.command.VisibiltyCommand;
import com.laurent.model.Customer;
import com.laurent.service.VisibilityByLocaleService;
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
public class VisibilityCommandByLocaleFactoryTest {
    @MockBean
    private VisibilityByLocaleService visibilityByLocaleService;

    @Autowired
    private VisibilityCommandByLocaleFactory visibilityCommandByLocaleFactory;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("john", "doe");
    }

    @Test
    public void doitRetournerUneCommandePourLocale() {
        // WHEN
        VisibiltyCommand visibilityCommand = visibilityCommandByLocaleFactory.createVisiblityCommand(customer, newSet("fr", "en"));

        // THEN
        assertThat(visibilityCommand)
                .isInstanceOf(VisibilityByLocaleCommand.class);

        assertThat(((VisibilityByLocaleCommand)visibilityCommand).getVisibilityByLocaleService())
                .isEqualTo(visibilityByLocaleService);
        assertThat(((VisibilityByLocaleCommand)visibilityCommand).getLocales())
                .containsOnly(Locale.FRENCH, Locale.ENGLISH);
    }
}