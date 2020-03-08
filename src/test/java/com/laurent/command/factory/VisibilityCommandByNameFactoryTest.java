package com.laurent.command.factory;

import com.laurent.VisibiltityTestConfig;
import com.laurent.command.VisibilityByNameCommand;
import com.laurent.command.VisibiltyCommand;
import com.laurent.service.VisibilityByNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.util.collections.Sets.newSet;

@RunWith(SpringRunner.class)
@Import(VisibiltityTestConfig.class)
public class VisibilityCommandByNameFactoryTest {
    @MockBean
    private VisibilityByNameService visibilityByNameService;

    @Autowired
    private VisibilityCommandByNameFactory visibilityCommandByNameFactory;

    @Test
    public void doitRetournerUneCommandePourName() {
        // WHEN
        VisibiltyCommand visibilityCommand = visibilityCommandByNameFactory.createVisibilityService(newSet("John", "Jane"));

        // THEN
        assertThat(visibilityCommand)
                .isInstanceOf(VisibilityByNameCommand.class);

        assertThat(((VisibilityByNameCommand)visibilityCommand).getVisibilityByNameService())
                .isEqualTo(visibilityByNameService);
        assertThat(((VisibilityByNameCommand)visibilityCommand).getNames())
                .containsOnly("John", "Jane");
    }
}