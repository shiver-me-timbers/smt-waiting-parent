package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.execution.WaitingFor;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class WaitingForFactoryTest {

    private LookupFactory<WaitingFor> lookupFactory;
    private WaitingForFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingForFactory(lookupFactory);
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void Can_create_a_waiting_for() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final ResultValidator validator = mock(ResultValidator.class);

        final WaitingFor expected = mock(WaitingFor.class);

        // Given
        given(lookupFactory.find(duration, unit, validator.getClass())).willReturn(expected);

        // When
        final WaitingFor actual = factory.create(duration, unit, validator);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void Can_add_a_waiting_for() {

        // Given
        final WaitingFor waitingFor = mock(WaitingFor.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final ResultValidator validator = mock(ResultValidator.class);

        // When
        factory.add(waitingFor, duration, unit, validator);

        // Then
        verify(lookupFactory).add(waitingFor, duration, unit, validator.getClass());
    }
}