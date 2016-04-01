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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public abstract class AbstractITWaiterTimeoutProperty extends AbstractITWaiterTimeout implements ITWaiterDefaults {

    @Rule
    public final PropertyRule properties = new PropertyRule();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Override
    public WaitingTimeout timeout(long duration, TimeUnit unit) {
        return new WaitingTimeout() {
            @Override
            public <T> T timeoutMethod(Callable<T> callable) throws Exception {
                return defaults().defaultsMethod(callable);
            }
        };
    }

    protected abstract WaitingTimeout overrideTimeout(long duration, TimeUnit unit);

    @Override
    public ExpectedException expectedException() {
        return expectedException;
    }

    @Override
    public void Can_change_the_timeout() throws Throwable {
        properties.setProperty("smt.waiting.timeout.duration", "200");
        properties.setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        super.Can_change_the_timeout();
    }

    @Override
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {
        properties.setProperty("smt.waiting.timeout.duration", "500");
        properties.setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        super.Can_wait_until_no_exception_is_thrown();
    }

    @Override
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {
        shortTimeout();
        super.Can_wait_until_time_out_if_exception_always_thrown();
    }

    @Override
    public void Can_directly_throw_a_runtime_exception() throws Throwable {
        shortTimeout();
        super.Can_directly_throw_a_runtime_exception();
    }

    @Override
    public void Can_directly_throw_an_error() throws Throwable {
        shortTimeout();
        super.Can_directly_throw_an_error();
    }

    @Test
    public void Can_override_the_timeout_system_properties() throws Throwable {

        final Callable callable = mock(Callable.class);
        final long start = System.currentTimeMillis();

        final Exception exception = new RuntimeException();

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", "SECONDS");
        given(callable.call()).willThrow(exception);

        // When
        try {
            overrideTimeout(200L, MILLISECONDS).timeoutMethod(callable);
        } catch (Exception e) {
            assertThat(e, is(exception));
        }

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }

    @Test
    public void Can_handle_invalid_time_unit_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final String invalidTimeUnit = someString();

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", invalidTimeUnit);
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString(invalidTimeUnit));

        // When
        defaults().defaultsMethod(callable);
    }

    private void shortTimeout() {
        properties.setProperty("smt.waiting.timeout.duration", "10");
        properties.setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
    }
}
