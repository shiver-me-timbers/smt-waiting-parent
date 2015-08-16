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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class WaiterTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Options options;
    private Waiter waiter;

    @Before
    public void setUp() {
        options = mock(Options.class);
        waiter = new Waiter(options);
    }

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Timer timer = mock(Timer.class);

        final Object expected = new Object();

        // Given
        given(options.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false);
        given(until.success()).willThrow(new RuntimeException()).willThrow(new RuntimeException()).willReturn(expected);
        given(options.isValid(expected)).willReturn(true);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
        verify(options, times(2)).interval();
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final String untilToString = someString();

        expectedException.expect(WaitedTooLongException.class);
        expectedException.expectMessage(format("Waited too long for (%s) to succeed", untilToString));
        expectedException.expectCause(isA(TestTimeOutException.class));

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class, untilToString);

        final Timer timer = mock(Timer.class);

        // Given
        given(options.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(new TestTimeOutException());

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        expectedException.expect(TestTimeOutRuntimeException.class);

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Timer timer = mock(Timer.class);

        // Given
        given(options.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(new TestTimeOutRuntimeException());

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        expectedException.expect(TestTimeOutError.class);

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Timer timer = mock(Timer.class);

        // Given
        given(options.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(new TestTimeOutError());

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_wait_until_result_is_valid() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Timer timer = mock(Timer.class);

        final Object expected = new Object();

        // Given
        given(options.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false);
        given(until.success()).willReturn(expected);
        given(options.isValid(expected)).willReturn(false, false, true);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
        verify(options, times(2)).interval();
    }

    @Test
    public void Can_wait_until_timeout_if_result_is_always_invalid() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Timer timer = mock(Timer.class);

        final Object expected = new Object();

        // Given
        given(options.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willReturn(expected);
        given(options.isValid(expected)).willReturn(false);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).success();
        verify(options, times(2)).interval();
    }

    @Test
    public void Can_wait_until_timeout_if_result_is_always_invalid_and_exception_was_thrown() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Timer timer = mock(Timer.class);

        final Object expected = new Object();

        // Given
        given(options.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(new Exception()).willReturn(expected);
        given(options.isValid(expected)).willReturn(false);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).success();
        verify(options, times(2)).interval();
    }

}