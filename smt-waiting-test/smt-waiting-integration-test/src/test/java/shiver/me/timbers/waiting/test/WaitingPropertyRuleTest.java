package shiver.me.timbers.waiting.test;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.data.random.RandomThings;
import shiver.me.timbers.waiting.property.PropertyManager;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.waiting.random.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.random.RandomExceptions.someThrowable;
import static shiver.me.timbers.waiting.util.Strings.classNames;
import static shiver.me.timbers.waiting.util.Strings.concat;

public class WaitingPropertyRuleTest {

    private PropertyManager properties;
    private WaitingPropertyRule rule;

    @Before
    public void setUp() {
        properties = mock(PropertyManager.class);
        rule = new WaitingPropertyRule(properties);
    }

    @Test
    public void Can_add_timeout_property() {

        // Given
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        // When
        final WaitingPropertyRule actual = rule.addTimeout(duration, unit);

        // Then
        assertThat(actual, is(rule));
        verify(properties).setProperty("smt.waiting.timeout.duration", String.valueOf(duration));
        verify(properties).setProperty("smt.waiting.timeout.unit", unit.name());
    }

    @Test
    public void Can_add_includes() {

        // Given
        final List<Throwable> includes = RandomThings.<Throwable>someThings(3, someThrowable(), someOtherThrowable());

        // When
        final WaitingPropertyRule actual = rule.addIncludesIfPresent(includes);

        // Then
        assertThat(actual, is(rule));
        verify(properties).setProperty("smt.waiting.includes", concat(classNames(includes), ","));
    }

    @Test
    public void Will_not_add_includes_if_non_are_supplied() {

        // When
        final WaitingPropertyRule actual = rule.addIncludesIfPresent(Collections.<Throwable>emptyList());

        // Then
        assertThat(actual, is(rule));
    }

    @Test
    public void Will_not_add_null_includes() {

        // When
        final WaitingPropertyRule actual = rule.addIncludesIfPresent(null);

        // Then
        assertThat(actual, is(rule));
    }

    @Test
    public void Can_add_excludes() {

        // Given
        final List<Throwable> excludes = RandomThings.<Throwable>someThings(3, someThrowable(), someOtherThrowable());

        // When
        final WaitingPropertyRule actual = rule.addExcludesIfPresent(excludes);

        // Then
        assertThat(actual, is(rule));
        verify(properties).setProperty("smt.waiting.excludes", concat(classNames(excludes), ","));
    }

    @Test
    public void Will_not_add_excludes_if_non_are_supplied() {

        // When
        final WaitingPropertyRule actual = rule.addExcludesIfPresent(Collections.<Throwable>emptyList());

        // Then
        assertThat(actual, is(rule));
    }

    @Test
    public void Will_not_add_null_excludes() {

        // When
        final WaitingPropertyRule actual = rule.addExcludesIfPresent(null);

        // Then
        assertThat(actual, is(rule));
    }
}