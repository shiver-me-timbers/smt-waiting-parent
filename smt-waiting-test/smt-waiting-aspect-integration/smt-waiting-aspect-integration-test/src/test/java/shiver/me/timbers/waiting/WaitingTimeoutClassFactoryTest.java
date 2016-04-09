package shiver.me.timbers.waiting;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class WaitingTimeoutClassFactoryTest {

    @Test
    public void Can_create_a_waiting_timeout_class_factory() {

        // When
        final WaitingTimeoutClassFactory actual = new WaitingTimeoutClassFactory();

        // Then
        assertThat(actual.create(200L, MILLISECONDS), instanceOf(CanChangeWaitingTimeoutClass.class));
        assertThat(actual.create(500L, MILLISECONDS), instanceOf(CanWaitUntilExceptionWaitingTimeoutClass.class));
        assertThat(actual.create(10L, MILLISECONDS), instanceOf(ShortWaitingTimeoutClass.class));
    }
}