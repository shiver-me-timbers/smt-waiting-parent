package shiver.me.timbers.waiting.factory;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingDefaults;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;

public class WaitingWithDefaultsFactoryTest {

    private LookupFactory<WaitingDefaults> lookupFactory;
    private WaitingWithDefaultsFactory factory;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        lookupFactory = mock(LookupFactory.class);
        factory = new WaitingWithDefaultsFactory(lookupFactory);
    }

    @Test
    public void Can_create_a_waiting_with_defaults() {

        final Boolean defaults = someBoolean();

        final WaitingDefaults expected = mock(WaitingDefaults.class);

        // Given
        given(lookupFactory.find(defaults)).willReturn(expected);

        // When
        final WaitingDefaults actual = factory.create(defaults);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_add_a_waiting_with_defaults() {

        // Given
        final WaitingDefaults waitingDefaults = mock(WaitingDefaults.class);
        final Boolean defaults = someBoolean();

        // When
        factory.add(waitingDefaults, defaults);

        // Then
        verify(lookupFactory).add(waitingDefaults, defaults);
    }
}