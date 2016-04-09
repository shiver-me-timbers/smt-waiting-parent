package shiver.me.timbers.waiting;

import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

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