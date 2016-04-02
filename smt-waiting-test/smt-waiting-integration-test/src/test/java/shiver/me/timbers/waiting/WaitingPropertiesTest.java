package shiver.me.timbers.waiting;

import org.junit.Test;
import shiver.me.timbers.data.random.RandomThings;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;
import static shiver.me.timbers.waiting.Strings.classNames;
import static shiver.me.timbers.waiting.Strings.concat;

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

    @Test
    public void Can_add_includes() {

        // Given
        final PropertyRule properties = mock(PropertyRule.class);
        final List<Throwable> includes = RandomThings.<Throwable>someThings(3, someThrowable(), someOtherThrowable());

        // When
        WaitingProperties.addIncludesIfPresent(properties, includes);

        // Then
        verify(properties).setProperty("smt.waiting.include", concat(classNames(includes), ","));
    }

    @Test
    public void Will_not_add_includes_if_non_are_supplied() {

        // Given
        final PropertyRule properties = mock(PropertyRule.class);

        // When
        WaitingProperties.addIncludesIfPresent(properties, Collections.<Throwable>emptyList());

        // Then
        verifyZeroInteractions(properties);
    }

    @Test
    public void Will_not_add_null_includes() {

        // Given
        final PropertyRule properties = mock(PropertyRule.class);

        // When
        WaitingProperties.addIncludesIfPresent(properties, null);

        // Then
        verifyZeroInteractions(properties);
    }

    @Test
    public void Can_add_excludes() {

        // Given
        final PropertyRule properties = mock(PropertyRule.class);
        final List<Throwable> excludes = RandomThings.<Throwable>someThings(3, someThrowable(), someOtherThrowable());

        // When
        WaitingProperties.addExcludesIfPresent(properties, excludes);

        // Then
        verify(properties).setProperty("smt.waiting.exclude", concat(classNames(excludes), ","));
    }

    @Test
    public void Will_not_add_excludes_if_non_are_supplied() {

        // Given
        final PropertyRule properties = mock(PropertyRule.class);

        // When
        WaitingProperties.addExcludesIfPresent(properties, Collections.<Throwable>emptyList());

        // Then
        verifyZeroInteractions(properties);
    }

    @Test
    public void Will_not_add_null_excludes() {

        // Given
        final PropertyRule properties = mock(PropertyRule.class);

        // When
        WaitingProperties.addExcludesIfPresent(properties, null);

        // Then
        verifyZeroInteractions(properties);
    }
}