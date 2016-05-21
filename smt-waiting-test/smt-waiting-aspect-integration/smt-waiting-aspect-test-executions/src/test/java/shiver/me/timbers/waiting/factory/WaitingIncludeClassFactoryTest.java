package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.CanIgnoreAllWaitingIncludeClass;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingIncludeClass;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_THROWABLES;

public class WaitingIncludeClassFactoryTest {

    @Test
    public void Can_create_a_waiting_include_class_factory() {

        // When
        final WaitingIncludeClassFactory actual = new WaitingIncludeClassFactory();

        // Then
        assertThat(actual.create(500L, MILLISECONDS, SOME_THROWABLES), instanceOf(CanIgnoreWaitingIncludeClass.class));
        assertThat(actual.create(500L, MILLISECONDS), instanceOf(CanIgnoreAllWaitingIncludeClass.class));
    }
}