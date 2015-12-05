/*
 * Copyright 2015 Karl Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
