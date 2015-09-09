package shiver.me.timbers.waiting;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StaticDefaultChoicesTest {

    @Test
    public void Can_create_default_choices() {

        // When
        final Choices actual = new StaticDefaultChoices().create();

        // Then
        assertThat(actual.getTimeoutDuration(), is(10L));
        assertThat(actual.getTimeoutUnit(), is(SECONDS));
        assertThat(actual.getIntervalDuration(), is(100L));
        assertThat(actual.getIntervalUnit(), is(MILLISECONDS));
        assertThat(actual.isWaitForTrue(), is(false));
        assertThat(actual.isWaitForNotNull(), is(false));
        assertThat(actual.getResultValidators(), empty());
    }
}