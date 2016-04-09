package shiver.me.timbers.waiting;

import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

public class WaitingIncludesWithExcludesClassFactoryTest {

    @Test
    public void Can_create_a_waiting_includes_with_excludes_class_factory() {

        // When
        final WaitingIncludesWithExcludesClassFactory actual = new WaitingIncludesWithExcludesClassFactory();

        // Then
        assertThat(
            actual.create(500L, MILLISECONDS, asList(SOME_THROWABLES), asList(SOME_OTHER_THROWABLES)),
            instanceOf(CanIgnoreIncludeExcludeWaitingIncludeClass.class)
        );
    }
}