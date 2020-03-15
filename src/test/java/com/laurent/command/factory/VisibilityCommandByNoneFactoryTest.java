package com.laurent.command.factory;

import com.laurent.VisibiltityTestConfig;
import com.laurent.command.VisibilityByNoneCommandService.VisibilityByNoneCommand;
import com.laurent.command.VisibiltyCommand;
import com.laurent.model.Customer;
import com.laurent.service.VisibilityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Import(VisibiltityTestConfig.class)
public class VisibilityCommandByNoneFactoryTest {
    @MockBean
    private VisibilityService visibilityService;

    @Autowired
    private VisibilityCommandByNoneFactory visibilityCommandByNoneFactory;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("john", "doe");
    }

    @Test
    public void doitRetournerUneCommandePourName() {
        // WHEN
        VisibiltyCommand visibilityCommand = visibilityCommandByNoneFactory.createVisiblityCommand(customer, null);

        // THEN
        assertThat(visibilityCommand)
                .isInstanceOf(VisibilityByNoneCommand.class);

        assertThat(((VisibilityByNoneCommand)visibilityCommand).getVisibilityService())
                .isEqualTo(visibilityService);
    }
}