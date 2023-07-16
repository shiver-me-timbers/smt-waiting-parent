package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingInterval;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class WaitingIntervalFactoryTest {

    private LookupFactory<WaitingInterval> lookupFactory;
    private WaitingIntervalFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingIntervalFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_interval() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        final WaitingInterval expected = mock(WaitingInterval.class);

        // Given
        given(lookupFactory.find(duration, unit)).willReturn(expected);

        // When
        final WaitingInterval actual = factory.create(duration, unit);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_interval() {

        // Given
        final WaitingInterval waitingInterval = mock(WaitingInterval.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        // When
        factory.add(waitingInterval, duration, unit);

        // Then
        verify(lookupFactory).add(waitingInterval, duration, unit);
    }
}