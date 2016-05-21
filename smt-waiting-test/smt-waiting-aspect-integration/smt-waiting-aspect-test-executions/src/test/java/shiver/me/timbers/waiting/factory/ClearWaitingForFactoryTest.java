package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.execution.WaitingFor;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class ClearWaitingForFactoryTest {

    private LookupFactory<WaitingFor> lookupFactory;
    private ClearWaitingForFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new ClearWaitingForFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_clear_waiting_for() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean clearWaitFor = someBoolean();
        final ResultValidator validator = mock(ResultValidator.class);

        final WaitingFor expected = mock(WaitingFor.class);

        // Given
        given(lookupFactory.find(duration, unit, clearWaitFor, validator.getClass())).willReturn(expected);

        // When
        final WaitingFor actual = factory.create(duration, unit, clearWaitFor, validator);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_for() {

        // Given
        final WaitingFor waitingFor = mock(WaitingFor.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Boolean clearWaitFor = someBoolean();
        final ResultValidator validator = mock(ResultValidator.class);

        // When
        factory.add(waitingFor, duration, unit, clearWaitFor, validator);

        // Then
        verify(lookupFactory).add(waitingFor, duration, unit, clearWaitFor, validator.getClass());
    }
}