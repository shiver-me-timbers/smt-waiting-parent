package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringWaiterConfiguration.class, ITSpringWaiterDurationInvalid.class})
@Configuration
@PropertySource("classpath:duration-invalid.properties")
@DirtiesContext(classMode = AFTER_CLASS)
public class ITSpringWaiterDurationInvalid {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_handle_invalid_time_unit_property() throws Throwable {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString("INVALID"));

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(new Exception());

        // When
        new SpringWaiter().wait(until);
    }
}
