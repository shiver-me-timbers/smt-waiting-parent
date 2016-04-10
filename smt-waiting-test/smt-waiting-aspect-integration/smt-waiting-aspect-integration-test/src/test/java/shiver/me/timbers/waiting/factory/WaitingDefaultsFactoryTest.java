package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingDefaults;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WaitingDefaultsFactoryTest {

    @Test
    public void Can_create_a_waiting_defaults() {

        // Given
        final WaitingDefaults expected = mock(WaitingDefaults.class);

        // When
        final WaitingDefaults actual = new WaitingDefaultsFactory(expected).create();

        // Then
        assertThat(actual, is(expected));
    }
}