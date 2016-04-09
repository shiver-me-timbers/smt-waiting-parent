package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilValidWaitingForClass;
import shiver.me.timbers.waiting.validation.ValidResult;

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