package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForTrueClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForTrueClass;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class WaitingForTrueClassFactoryTest {

    @Test
    public void Can_create_a_waiting_for_true_class_factory() {

        // When
        final WaitingForTrueClassFactory actual = new WaitingForTrueClassFactory();

        // Then
        assertThat(actual.create(500L, MILLISECONDS, true), instanceOf(CanWaitUntilWaitingForTrueClass.class));
        assertThat(actual.create(200L, MILLISECONDS, true), instanceOf(CanWaitUntilTimeoutWaitingForTrueClass.class));
    }
}