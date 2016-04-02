package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class PropertyWaitingIncludeAndExcludeTest {

    private WaitingPropertyRule properties;
    private Long duration;
    private TimeUnit unit;
    private List<Throwable> includes;
    private List<Throwable> excludes;
    private PropertyWaitingIncludeAndExclude waitingIncludeAndExclude;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        properties = mock(WaitingPropertyRule.class);
        duration = someLong();
        unit = someEnum(TimeUnit.class);
        includes = mock(List.class);
        excludes = mock(List.class);
        waitingIncludeAndExclude = new PropertyWaitingIncludeAndExclude(properties, duration, unit, includes, excludes) {
            @Override
            protected <T> T method(Callable<T> callable) throws Exception {
                return callable.call();
            }
        };
    }

    @Test
    public void Can_add_timeout_includes_and_excludes_for_include_method() throws Exception {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willReturn(expected);

        // When
        final Object actual = waitingIncludeAndExclude.includeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(properties).addTimeout(duration, unit);
        verify(properties).addIncludesIfPresent(includes);
        verify(properties).addExcludesIfPresent(excludes);
    }

    @Test
    public void Can_add_timeout_includes_and_excludes_for_exclude_method() throws Exception {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willReturn(expected);

        // When
        final Object actual = waitingIncludeAndExclude.excludeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(properties).addTimeout(duration, unit);
        verify(properties).addIncludesIfPresent(includes);
        verify(properties).addExcludesIfPresent(excludes);
    }
}