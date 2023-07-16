package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingInclude;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class ClearWaitingIncludeFactoryTest {

    private LookupFactory<WaitingInclude> lookupFactory;
    private ClearWaitingIncludeFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new ClearWaitingIncludeFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_clear_waiting_include() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean clearIncludes = someBoolean();
        final Throwable include = mock(Throwable.class);

        final WaitingInclude expected = mock(WaitingInclude.class);

        // Given
        given(lookupFactory.find(duration, unit, clearIncludes, include.getClass())).willReturn(expected);

        // When
        final WaitingInclude actual = factory.create(duration, unit, clearIncludes, include);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_include() {

        // Given
        final WaitingInclude waitingInclude = mock(WaitingInclude.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean clearIncludes = someBoolean();
        final Throwable include = mock(Throwable.class);

        // When
        factory.add(waitingInclude, duration, unit, clearIncludes, include);

        // Then
        verify(lookupFactory).add(waitingInclude, duration, unit, clearIncludes, include.getClass());
    }
}