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

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public abstract class AbstractITWaiterWaitFor implements ITWaiterWaitFor {

    private final ValidResult validator = new ValidResult();

    @Test
    @Override
    public void Can_wait_until_valid_result_is_returned() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = "valid";

        // Given
        given(callable.call()).willReturn(someString(), someString(), expected);

        // When
        final Object actual = waitFor(500L, MILLISECONDS, validator).waitForMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = someString();

        // Given
        given(callable.call()).willReturn(expected);

        // When
        final Object actual = waitFor(200L, MILLISECONDS, validator).waitForMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, atLeast(2)).call();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_and_an_exception_was_thrown() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = someString();

        // Given
        given(callable.call()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = waitFor(200L, MILLISECONDS, validator).waitForMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, atLeast(2)).call();
    }
}
