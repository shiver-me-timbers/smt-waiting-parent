package shiver.me.timbers.waiting;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class WaitingForClassFactoryTest {

    @Test
    public void Can_create_a_waiting_for_class_factory() {

        // When
        final WaitingForClassFactory actual = new WaitingForClassFactory();

        // Then
        final ValidResult validator = new ValidResult();
        assertThat(actual.create(500L, MILLISECONDS, validator), instanceOf(CanWaitUntilValidWaitingForClass.class));
        assertThat(actual.create(200L, MILLISECONDS, validator), instanceOf(CanWaitUntilTimeoutWaitingForClass.class));
    }
}