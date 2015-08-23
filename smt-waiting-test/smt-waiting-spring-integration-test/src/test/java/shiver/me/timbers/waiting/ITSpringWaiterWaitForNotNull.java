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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringWaiterConfiguration.class, ITSpringWaiterWaitForNotNull.class})
@Configuration
@PropertySource("classpath:wait-for-not-null.properties")
@DirtiesContext(classMode = AFTER_CLASS)
public class ITSpringWaiterWaitForNotNull {

    @Test
    public void Can_set_wait_for_not_null_with_spring_properties() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willReturn(null, null, expected);

        // When
        final Object actual = new SpringWaiter().wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }
}
