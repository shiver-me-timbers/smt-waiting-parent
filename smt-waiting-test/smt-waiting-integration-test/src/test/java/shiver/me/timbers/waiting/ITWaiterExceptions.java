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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ITWaiterExceptions {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private Waiter waiter;
    private Waiter shortWaiter;

    @Before
    public void setUp() {
        waiter = new Waiter(new Options().withTimeout(500L, MILLISECONDS));
        shortWaiter = new Waiter(new Options().withTimeout(10L, MILLISECONDS));
    }

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willThrow(new Exception()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_no_exception_is_thrown_with_defaults() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willThrow(new Exception()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final Until until = mock(Until.class);

        final Exception exception = new TestTimeOutException();

        // Given
        given(until.success()).willThrow(exception);
        expectedException.expect(WaitedTooLongException.class);
        expectedException.expectCause(is(exception));

        // When
        shortWaiter.wait(until);
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        final Until until = mock(Until.class);

        final Exception exception = new TestTimeOutRuntimeException();

        // Given
        given(until.success()).willThrow(exception);
        expectedException.expect(is(exception));

        // When
        shortWaiter.wait(until);
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        final Until until = mock(Until.class);

        final Error error = new TestTimeOutError();

        // Given
        given(until.success()).willThrow(error);
        expectedException.expect(is(error));

        // When
        shortWaiter.wait(until);
    }
}
