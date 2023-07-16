package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForNotNullClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForNotNullClass;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class WaitingForNotNullClassFactoryTest {

    @Test
    public void Can_create_a_waiting_for_not_null_class_factory() {

        // When
        final WaitingForNotNullClassFactory actual = new WaitingForNotNullClassFactory();

        // Then
        assertThat(actual.create(500L, MILLISECONDS, true), instanceOf(CanWaitUntilWaitingForNotNullClass.class));
        assertThat(actual.create(200L, MILLISECONDS, true), instanceOf(CanWaitUntilTimeoutWaitingForNotNullClass.class));
    }
}