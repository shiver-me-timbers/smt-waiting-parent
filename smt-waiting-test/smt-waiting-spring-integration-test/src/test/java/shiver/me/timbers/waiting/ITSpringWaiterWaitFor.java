package shiver.me.timbers.waiting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static shiver.me.timbers.data.random.RandomStrings.someString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringWaiterConfiguration.class, ITSpringWaiterWaitFor.class})
@Configuration
@PropertySource("classpath:wait-for.properties")
@DirtiesContext(classMode = AFTER_CLASS)
public class ITSpringWaiterWaitFor {

    @Test
    public void Can_set_wait_for_with_spring_properties() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = "valid";

        // Given
        given(until.success()).willReturn(someString(), someString(), expected);

        // When
        final Object actual = new SpringWaiter().wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }
}
