package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class SystemPropertyGetterTest {

    private SystemPropertyGetter getter;

    @Before
    public void setUp() {
        getter = new SystemPropertyGetter();
    }

    @Test
    public void Can_get_a_system_property() {

        final String key = someAlphaNumericString();

        final String expected = someString();

        // Given
        System.setProperty(key, expected);

        // When
        final String actual = getter.get(key);

        // Then
        System.clearProperty(key);
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_a_system_property_while_supplying_a_default_value() {

        final String key = someAlphaNumericString();

        final String expected = someString();

        // Given
        System.setProperty(key, expected);

        // When
        final String actual = getter.get(key, new Object());

        // Then
        System.clearProperty(key);
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_a_default_system_property() {

        // Given
        final String key = someAlphaNumericString();
        final Object expected = new Object();

        // When
        final String actual = getter.get(key, expected);

        // Then
        System.clearProperty(key);
        assertThat(actual, is(expected.toString()));
    }
}