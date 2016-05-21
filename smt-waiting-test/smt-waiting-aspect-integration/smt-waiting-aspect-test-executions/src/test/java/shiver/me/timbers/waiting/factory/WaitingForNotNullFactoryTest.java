package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingForNotNull;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class WaitingForNotNullFactoryTest {

    private LookupFactory<WaitingForNotNull> lookupFactory;
    private WaitingForNotNullFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingForNotNullFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_for_not_null() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean isNotNull = someBoolean();

        final WaitingForNotNull expected = mock(WaitingForNotNull.class);

        // Given
        given(lookupFactory.find(duration, unit, isNotNull)).willReturn(expected);

        // When
        final WaitingForNotNull actual = factory.create(duration, unit, isNotNull);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_for_not_null() {

        // Given
        final WaitingForNotNull waitingForNotNull = mock(WaitingForNotNull.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean isNotNull = someBoolean();

        // When
        factory.add(waitingForNotNull, duration, unit, isNotNull);

        // Then
        verify(lookupFactory).add(waitingForNotNull, duration, unit, isNotNull);
    }
}