package shiver.me.timbers.waiting;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NotNullResultTest {

    @Test
    public void Can_check_for_a_not_null_result() throws Throwable {

        // When
        final boolean actual = new NotNullResult().isValid(new Object());

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void Can_check_for_a_null_result() throws Throwable {

        // When
        final boolean actual = new NotNullResult().isValid(null);

        // Then
        assertThat(actual, is(false));
    }
}