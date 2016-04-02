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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public abstract class AbstractITWaiterIntervalProperty extends AbstractITWaiterInterval
    implements ITWaiterDefaults, WaitingPropertyRuleAware {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Override
    public WaitingInterval interval(final long duration, final TimeUnit unit) {
        return new WaitingInterval() {
            @Override
            public <T> T intervalMethod(Callable<T> callable) throws Exception {
                properties().setProperty("smt.waiting.interval.duration", String.valueOf(duration));
                properties().setProperty("smt.waiting.interval.unit", unit.name());
                return defaults().defaultsMethod(callable);
            }
        };
    }

    protected abstract WaitingInterval overrideInterval(long duration, TimeUnit unit);

    @Test
    public void Can_override_the_interval_system_properties() throws Throwable {

        final Callable callable = mock(Callable.class);
        final long start = System.currentTimeMillis();

        // Given
        properties().setProperty("smt.waiting.interval.duration", "1");
        properties().setProperty("smt.waiting.interval.unit", "SECONDS");
        given(callable.call()).willThrow(new Exception()).willReturn(new Object());

        // When
        overrideInterval(200L, MILLISECONDS).intervalMethod(callable);

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }

    @Test
    public void Can_handle_invalid_time_unit_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final String invalidTimeUnit = someString();

        // Given
        properties().setProperty("smt.waiting.interval.duration", "1");
        properties().setProperty("smt.waiting.interval.unit", invalidTimeUnit);
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString(invalidTimeUnit));

        // When
        defaults().defaultsMethod(callable);
    }
}
