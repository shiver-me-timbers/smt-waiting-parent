package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingExclude;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class ClearWaitingExcludeFactoryTest {

    private LookupFactory<WaitingExclude> lookupFactory;
    private ClearWaitingExcludeFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new ClearWaitingExcludeFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_clear_waiting_exclude() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean clearExcludes = someBoolean();
        final Throwable include = mock(Throwable.class);

        final WaitingExclude expected = mock(WaitingExclude.class);

        // Given
        given(lookupFactory.find(duration, unit, clearExcludes, include.getClass())).willReturn(expected);

        // When
        final WaitingExclude actual = factory.create(duration, unit, clearExcludes, include);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_exclude() {

        // Given
        final WaitingExclude waitingExclude = mock(WaitingExclude.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean clearExcludes = someBoolean();
        final Throwable include = mock(Throwable.class);

        // When
        factory.add(waitingExclude, duration, unit, clearExcludes, include);

        // Then
        verify(lookupFactory).add(waitingExclude, duration, unit, clearExcludes, include.getClass());
    }
}