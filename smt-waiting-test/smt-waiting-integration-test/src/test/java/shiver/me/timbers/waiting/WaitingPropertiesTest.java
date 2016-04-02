package shiver.me.timbers.waiting;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class WaitingPropertiesTest {

    @Test
    public void Can_add_timeout_property() {

        // Given
        final PropertyRule properties = mock(PropertyRule.class);
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        // When
        WaitingProperties.addTimeout(properties, duration, unit);

        // Then
        verify(properties).setProperty("smt.waiting.timeout.duration", String.valueOf(duration));
        verify(properties).setProperty("smt.waiting.timeout.unit", unit.name());
    }
}