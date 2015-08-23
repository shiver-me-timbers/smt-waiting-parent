package shiver.me.timbers.waiting;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class WasDurationMatcher extends TypeSafeMatcher<Object> {

    public static Matcher<?> durationWas(final long expectedDuration, final TimeUnit unit) {
        return new WasDurationMatcher(unit, expectedDuration);
    }

    private static final int HALF_SECOND = 500;

    private final long start;
    private final TimeUnit unit;
    private final long expectedDuration;
    private String message;

    public WasDurationMatcher(TimeUnit unit, long expectedDuration) {
        this.unit = unit;
        this.expectedDuration = expectedDuration;
        this.start = System.currentTimeMillis();
        this.message = "";
    }

    @Override
    protected boolean matchesSafely(Object object) {
        final long now = System.currentTimeMillis();
        final long duration = (now - start);
        final long expectedDurationMillis = unit.toMillis(expectedDuration);

        if (expectedDurationMillis > duration) {
            message = format(
                "duration wasn't long enough. Expected %s MILLISECONDS, but was %s MILLISECONDS.",
                expectedDurationMillis,
                duration
            );
            return false;
        }
        if (duration > expectedDurationMillis + HALF_SECOND) {
            message = format(
                "duration was too long. Expected less than %s + %s MILLISECONDS, but was %s MILLISECONDS.",
                expectedDurationMillis,
                HALF_SECOND,
                duration
            );
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }
}
