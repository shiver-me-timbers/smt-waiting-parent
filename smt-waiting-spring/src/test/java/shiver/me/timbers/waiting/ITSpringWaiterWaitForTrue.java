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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringWaiterConfiguration.class, ITSpringWaiterWaitForTrue.class})
@Configuration
@PropertySource("wait-for-true.properties")
public class ITSpringWaiterWaitForTrue {

    @Test
    public void Can_set_wait_for_true_with_system_properties() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willReturn(false, false, true);

        // When
        final Object actual = new SpringWaiter().wait(until);

        // Then
        assertThat(actual, equalTo((Object) true));
        verify(until, times(3)).success();
    }
}
