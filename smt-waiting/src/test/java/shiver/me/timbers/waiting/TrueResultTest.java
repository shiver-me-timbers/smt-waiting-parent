package shiver.me.timbers.waiting;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;

public class TrueResultTest {

    @Test
    public void Can_check_for_a_boolean_result() throws Throwable {

        // Given
        final Boolean expected = someBoolean();

        // When
        final boolean actual = new TrueResult().isValid(expected);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_check_for_a_string_result() throws Throwable {

        // Given
        final String string = "true";

        // When
        final boolean actual = new TrueResult().isValid(string);

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void Can_check_for_an_object_result() throws Throwable {

        // Given
        final Object object = new Object();

        // When
        final boolean actual = new TrueResult().isValid(object);

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void Can_check_for_a_null_result() throws Throwable {

        // When
        final boolean actual = new TrueResult().isValid(null);

        // Then
        assertThat(actual, is(false));
    }
}