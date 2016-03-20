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

    @Before
    public void setUp() {
        options = mock(Options.class);
    }

    @Test
    public void can_create_a_default_waiter() {

        // When
        new Waiter();
    }

    @Test
    public void can_create_a_waiter() {

        // When
        new Waiter(options);

        // Then
        verify(options).choose();
    }

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);
        final RuntimeException exception = new RuntimeException();

        final Object expected = new Object();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false);
        given(until.success()).willThrow(exception).willThrow(exception).willReturn(expected);
        given(choice.isSuppressed(exception)).willReturn(true);
        given(choice.isValid(expected)).willReturn(true);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
        verify(choice, times(2)).interval();
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final String untilToString = someString();

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class, untilToString);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);
        final TestTimeOutException exception = new TestTimeOutException();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(exception);
        expectedException.expect(WaitedTooLongException.class);
        expectedException.expectMessage(format("Waited too long for (%s) to succeed", untilToString));
        expectedException.expectCause(is(exception));

        // When
        new Waiter(options).wait(until);
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {


        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);
        final TestTimeOutRuntimeException exception = new TestTimeOutRuntimeException();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(exception);
        expectedException.expect(is(exception));

        // When
        new Waiter(options).wait(until);
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);
        final TestTimeOutError error = new TestTimeOutError();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(error);
        expectedException.expect(is(error));

        // When
        new Waiter(options).wait(until);
    }

    @Test
    public void Can_wait_until_result_is_valid() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);

        final Object expected = new Object();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false);
        given(until.success()).willReturn(expected);
        given(choice.isValid(expected)).willReturn(false, false, true);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
        verify(choice, times(2)).interval();
    }

    @Test
    public void Can_wait_until_timeout_if_result_is_always_invalid() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);

        final Object expected = new Object();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willReturn(expected);
        given(choice.isValid(expected)).willReturn(false);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).success();
        verify(choice, times(2)).interval();
    }

    @Test
    public void Can_wait_until_timeout_if_result_is_always_invalid_and_exception_was_thrown() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);
        final Exception exception = new Exception();

        final Object expected = new Object();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false, false, true);
        given(until.success()).willThrow(exception).willReturn(expected);
        given(choice.isSuppressed(exception)).willReturn(true);
        given(choice.isValid(expected)).willReturn(false);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).success();
        verify(choice, times(2)).interval();
    }

    @Test
    public void Can_ignore_exceptions_contained_in_the_include_list() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);
        final TestTimeOutException exception = new TestTimeOutException();

        final Object expected = new Object();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false);
        given(until.success()).willThrow(exception).willReturn(expected);
        given(choice.isSuppressed(exception)).willReturn(true);
        given(choice.isValid(expected)).willReturn(true);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).success();
    }

    @Test
    public void Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list() throws Throwable {

        @SuppressWarnings("unchecked")
        final Until<Object> until = mock(Until.class);

        final Choice choice = mock(Choice.class);
        final Timer timer = mock(Timer.class);
        final TestTimeOutRuntimeException exception = new TestTimeOutRuntimeException();

        // Given
        given(options.choose()).willReturn(choice);
        given(choice.startTimer()).willReturn(timer);
        given(timer.exceeded()).willReturn(false);
        given(until.success()).willThrow(exception);
        given(choice.isSuppressed(exception)).willReturn(false);
        expectedException.expect(is(exception));

        // When
        new Waiter(options).wait(until);
    }
}