package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomThings.someThings;
import static shiver.me.timbers.waiting.Classes.toClasses;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public class WaitingExcludesWithIncludesFactoryTest {

    private LookupFactory<WaitingExclude> lookupFactory;
    private WaitingExcludesWithIncludesFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingExcludesWithIncludesFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_exclude() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final List<Throwable> excludes = someThings(someThrowable(), someOtherThrowable());
        final List<Throwable> includes = someThings(someThrowable(), someOtherThrowable());

        final WaitingExclude expected = mock(WaitingExclude.class);

        // Given
        given(lookupFactory.find(duration, unit, toClasses(excludes), toClasses(includes))).willReturn(expected);

        // When
        final WaitingExclude actual = factory.create(duration, unit, excludes, includes);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_exclude() {

        // Given
        final WaitingExclude waitingExclude = mock(WaitingExclude.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final List<Throwable> excludes = someThings(someThrowable(), someOtherThrowable());
        final List<Throwable> includes = someThings(someThrowable(), someOtherThrowable());

        // When
        factory.add(waitingExclude, duration, unit, excludes, includes);

        // Then
        verify(lookupFactory).add(waitingExclude, duration, unit, toClasses(excludes), toClasses(includes));
    }
}