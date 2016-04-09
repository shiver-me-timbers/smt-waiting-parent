package shiver.me.timbers.waiting;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class WaitingIntervalClassFactoryTest {

    @Test
    public void Can_create_a_waiting_interval_class_factory() {

        // When
        final WaitingIntervalClassFactory actual = new WaitingIntervalClassFactory();

        // Then
        assertThat(actual.create(200L, MILLISECONDS), instanceOf(CanChangeWaitingIntervalClass.class));
    }
}