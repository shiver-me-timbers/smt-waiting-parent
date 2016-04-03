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

public class WaitingIncludesWithExcludesFactoryTest {

    private LookupFactory<WaitingInclude> lookupFactory;
    private WaitingIncludesWithExcludesFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingIncludesWithExcludesFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_include() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final List<Throwable> excludes = someThings(someThrowable(), someOtherThrowable());
        final List<Throwable> includes = someThings(someThrowable(), someOtherThrowable());

        final WaitingInclude expected = mock(WaitingInclude.class);

        // Given
        given(lookupFactory.find(duration, unit, toClasses(includes), toClasses(excludes))).willReturn(expected);

        // When
        final WaitingInclude actual = factory.create(duration, unit, includes, excludes);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_include() {

        // Given
        final WaitingInclude waitingInclude = mock(WaitingInclude.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final List<Throwable> excludes = someThings(someThrowable(), someOtherThrowable());
        final List<Throwable> includes = someThings(someThrowable(), someOtherThrowable());

        // When
        factory.add(waitingInclude, duration, unit, includes, excludes);

        // Then
        verify(lookupFactory).add(waitingInclude, duration, unit, toClasses(includes), toClasses(excludes));
    }
}