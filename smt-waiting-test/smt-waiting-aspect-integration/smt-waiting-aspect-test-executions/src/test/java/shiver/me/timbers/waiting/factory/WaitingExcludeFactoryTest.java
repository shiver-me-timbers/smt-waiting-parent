package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingExclude;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomThings.someThings;
import static shiver.me.timbers.waiting.random.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.random.RandomExceptions.someThrowable;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class WaitingExcludeFactoryTest {

    private LookupFactory<WaitingExclude> lookupFactory;
    private WaitingExcludeFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingExcludeFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_exclude_with_some_throwable() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final List<Throwable> excludes = someThings(someThrowable(), someOtherThrowable());

        final WaitingExclude expected = mock(WaitingExclude.class);

        // Given
        given(lookupFactory.find(duration, unit, toClasses(excludes))).willReturn(expected);

        // When
        final WaitingExclude actual = factory.create(duration, unit, excludes.toArray(new Throwable[excludes.size()]));

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

        // When
        factory.add(waitingExclude, duration, unit, excludes.toArray(new Throwable[excludes.size()]));

        // Then
        verify(lookupFactory).add(waitingExclude, duration, unit, toClasses(excludes));
    }
}