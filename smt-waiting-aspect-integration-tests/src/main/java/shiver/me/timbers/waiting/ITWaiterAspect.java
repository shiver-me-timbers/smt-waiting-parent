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
import shiver.me.timbers.waiting.test.TestTimeOutError;
import shiver.me.timbers.waiting.test.TestTimeOutException;
import shiver.me.timbers.waiting.test.TestTimeOutRuntimeException;
import shiver.me.timbers.waiting.test.WaitingDefaultsComponent;
import shiver.me.timbers.waiting.test.WaitingDurationComponent;
import shiver.me.timbers.waiting.test.WaitingForComponent;
import shiver.me.timbers.waiting.test.WaitingForNotNullComponent;
import shiver.me.timbers.waiting.test.WaitingForTrueComponent;
import shiver.me.timbers.waiting.test.WaitingIntervalComponent;

import java.util.concurrent.Callable;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public abstract class ITWaiterAspect {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    protected abstract WaitingDefaultsComponent defaultsComponent();

    protected abstract WaitingDurationComponent durationComponent();

    protected abstract WaitingForComponent waitingForComponent();

    protected abstract WaitingForTrueComponent forTrueComponent();

    protected abstract WaitingForNotNullComponent notNullComponent();

    protected abstract WaitingIntervalComponent intervalComponent();

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Exception {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willThrow(new Exception()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = defaultsComponent().defaultsMethod(callable);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final String callableToString = someString();

        expectedException.expect(WaitedTooLongException.class);
        expectedException.expectMessage(containsString("durationSetMethod"));
        expectedException.expectCause(isA(TestTimeOutException.class));

        final Callable callable = mock(Callable.class, callableToString);

        // Given
        given(callable.call()).willThrow(new TestTimeOutException());

        // When
        durationComponent().durationSetMethod(callable);
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        expectedException.expect(TestTimeOutRuntimeException.class);

        final Callable callable = mock(Callable.class);

        // Given
        given(callable.call()).willThrow(new TestTimeOutRuntimeException());

        // When
        durationComponent().durationSetMethod(callable);
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        expectedException.expect(TestTimeOutError.class);

        final Callable callable = mock(Callable.class);

        // Given
        given(callable.call()).willThrow(new TestTimeOutError());

        // When
        durationComponent().durationSetMethod(callable);
    }

    @Test
    public void Can_wait_until_valid_result_is_returned() throws Throwable {

        @SuppressWarnings("unchecked")
        final Callable<String> callable = mock(Callable.class);

        final String expected = "valid";

        // Given
        given(callable.call()).willReturn(someString(), someString(), expected);

        // When
        final String actual = waitingForComponent().waitingForMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned() throws Throwable {

        @SuppressWarnings("unchecked")
        final Callable<String> callable = mock(Callable.class);

        final String expected = someString();

        // Given
        given(callable.call()).willReturn(expected);

        // When
        final String actual = waitingForComponent().waitingForMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, atLeast(2)).call();
    }

    @Test
    public void Can_wait_until_true_is_returned() throws Throwable {

        @SuppressWarnings("unchecked")
        final Callable<Boolean> callable = mock(Callable.class);

        // Given
        given(callable.call()).willReturn(false, false, true);

        // When
        final boolean actual = forTrueComponent().waitForTrueMethod(callable);

        // Then
        assertThat(actual, is(true));
        verify(callable, times(3)).call();
    }

    @Test
    public void Can_wait_until_time_out_for_true_when_false_always_returned() throws Throwable {

        @SuppressWarnings("unchecked")
        final Callable<Boolean> callable = mock(Callable.class);

        // Given
        given(callable.call()).willReturn(false);

        // When
        final boolean actual = forTrueComponent().waitForTrueMethod(callable);

        // Then
        assertThat(actual, is(false));
        verify(callable, atLeast(2)).call();
    }

    @Test
    public void Can_wait_until_time_out_for_true_when_null_always_returned() throws Throwable {

        @SuppressWarnings("unchecked")
        final Callable<Boolean> callable = mock(Callable.class);

        // Given
        given(callable.call()).willReturn(null);

        // When
        final Boolean actual = forTrueComponent().waitForTrueMethod(callable);

        // Then
        assertThat(actual, nullValue());
        verify(callable, atLeast(2)).call();
    }

    @Test
    public void Can_wait_for_a_non_null_value() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willReturn(null, null, expected);

        // When
        final Object actual = notNullComponent().waitForNotNullMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    public void Can_wait_until_time_out_for_non_null_when_null_always_returned() throws Throwable {

        final Callable callable = mock(Callable.class);

        // Given
        given(callable.call()).willReturn(null);

        // When
        final Object actual = notNullComponent().waitForNotNullMethod(callable);

        // Then
        assertThat(actual, nullValue());
        verify(callable, atLeast(2)).call();
    }

    @Test
    public void Can_change_the_interval() throws Throwable {

        final Callable callable = mock(Callable.class);
        final long start = System.currentTimeMillis();

        // Given
        given(callable.call()).willThrow(new Exception()).willReturn(new Object());

        // When
        intervalComponent().intervalMethod(callable);

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }
}
