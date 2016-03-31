/*
 * Copyright 2016 Karl Bennett
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

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.waiting.WasDurationMatcher.durationWas;

public abstract class AbstractITWaiterTimeout implements ITWaiterTimeout {

    protected abstract ExpectedException expectedException();

    @Test
    @Override
    public void Can_change_the_timeout() throws Throwable {

        final Callable callable = mock(Callable.class);
        final long start = System.currentTimeMillis();

        final Exception exception = new RuntimeException();

        // Given
        given(callable.call()).willThrow(exception);

        // When
        try {
            timeout(200L, MILLISECONDS).timeoutMethod(callable);
        } catch (Exception e) {
            assertThat(e, is(exception));
        }

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }

    @Test
    @Override
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willThrow(new Exception()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = timeout(500L, MILLISECONDS).timeoutMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Exception exception = new TestTimeOutException();

        // Given
        given(callable.call()).willThrow(exception);
        expectedException().expect(WaitedTooLongException.class);
        expectedException().expectMessage(containsString("Waited too long for"));
        expectedException().expectCause(is(exception));
        expectedException().expect(durationWas(10, MILLISECONDS));

        // When
        shortTimeout().timeoutMethod(callable);
    }

    @Test
    @Override
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Exception exception = new TestTimeOutRuntimeException();

        // Given
        given(callable.call()).willThrow(exception);
        expectedException().expect(is(exception));
        expectedException().expect(durationWas(10, MILLISECONDS));

        // When
        shortTimeout().timeoutMethod(callable);
    }

    @Test
    @Override
    public void Can_directly_throw_an_error() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Error error = new TestTimeOutError();

        // Given
        given(callable.call()).willThrow(error);
        expectedException().expect(is(error));
        expectedException().expect(durationWas(10, MILLISECONDS));

        // When
        shortTimeout().timeoutMethod(callable);
    }

    private WaitingTimeout shortTimeout() {
        return timeout(10L, MILLISECONDS);
    }
}
