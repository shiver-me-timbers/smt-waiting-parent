package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingTimeout;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class WaitingTimeoutFactoryTest {

    private LookupFactory<WaitingTimeout> lookupFactory;
    private WaitingTimeoutFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingTimeoutFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_timeout() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        final WaitingTimeout expected = mock(WaitingTimeout.class);

        // Given
        given(lookupFactory.find(duration, unit)).willReturn(expected);

        // When
        final WaitingTimeout actual = factory.create(duration, unit);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_timeout() {

        // Given
        final WaitingTimeout waitingTimeout = mock(WaitingTimeout.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        // When
        factory.add(waitingTimeout, duration, unit);

        // Then
        verify(lookupFactory).add(waitingTimeout, duration, unit);
    }
}