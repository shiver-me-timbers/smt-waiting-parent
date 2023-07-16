package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.CannotIgnoreExcludeIncludeWaitingExcludeClass;
import shiver.me.timbers.waiting.execution.ExcludePrecedenceWaitingExcludeClass;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_THROWABLES;

public class WaitingExcludesWithIncludesClassFactoryTest {

    @Test
    public void Can_create_a_waiting_excludes_with_includes_class_factory() {

        // When
        final WaitingExcludesWithIncludesClassFactory actual = new WaitingExcludesWithIncludesClassFactory();

        // Then
        assertThat(
            actual.create(500L, MILLISECONDS, asList(SOME_THROWABLES), asList(SOME_OTHER_THROWABLES)),
            instanceOf(CannotIgnoreExcludeIncludeWaitingExcludeClass.class)
        );
        assertThat(
            actual.create(500L, MILLISECONDS, singletonList(SOME_THROWABLES[0]), singletonList(SOME_THROWABLES[0])),
            instanceOf(ExcludePrecedenceWaitingExcludeClass.class)
        );
    }
}