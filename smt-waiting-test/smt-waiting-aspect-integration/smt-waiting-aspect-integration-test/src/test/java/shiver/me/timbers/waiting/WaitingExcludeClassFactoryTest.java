package shiver.me.timbers.waiting;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

public class WaitingExcludeClassFactoryTest {

    @Test
    public void Can_create_a_waiting_exclude_class_factory() {

        // When
        final WaitingExcludeClassFactory actual = new WaitingExcludeClassFactory();

        // Then
        assertThat(
            actual.create(500L, MILLISECONDS, SOME_THROWABLES[0], SOME_OTHER_THROWABLES[0], SOME_THROWABLES[1]),
            instanceOf(CannotIgnoreWaitingExcludeClass.class)
        );
        assertThat(
            actual.create(500L, MILLISECONDS, SOME_OTHER_THROWABLES),
            instanceOf(CanIgnoreWaitingExcludeClass.class)
        );
    }
}