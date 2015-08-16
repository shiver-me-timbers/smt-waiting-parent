package shiver.me.timbers.waiting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringWaiterConfiguration.class, ITSpringWaiterDuration.class})
@Configuration
@PropertySource("duration.properties")
public class ITSpringWaiterDuration {

    @Test
    public void Can_configure_waiter_with_spring_properties() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willReturn(false);

        // When
        final Object actual = new SpringWaiter().wait(until);

        // Then
        assertThat(actual, equalTo((Object) false));
        verify(until, atMost(3)).success();
    }
}
