package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingForTrue;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class WaitingForTrueFactoryTest {

    private LookupFactory<WaitingForTrue> lookupFactory;
    private WaitingForTrueFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingForTrueFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_for_true() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean isTrue = someBoolean();

        final WaitingForTrue expected = mock(WaitingForTrue.class);

        // Given
        given(lookupFactory.find(duration, unit, isTrue)).willReturn(expected);

        // When
        final WaitingForTrue actual = factory.create(duration, unit, isTrue);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_for_true() {

        // Given
        final WaitingForTrue waitingForTrue = mock(WaitingForTrue.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean isTrue = someBoolean();

        // When
        factory.add(waitingForTrue, duration, unit, isTrue);

        // Then
        verify(lookupFactory).add(waitingForTrue, duration, unit, isTrue);
    }
}