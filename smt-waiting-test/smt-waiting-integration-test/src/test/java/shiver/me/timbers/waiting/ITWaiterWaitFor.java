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
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class ITWaiterWaitFor {

    private Options options;

    @Before
    public void setUp() {
        options = new Options().withTimeout(500L, MILLISECONDS);
    }

    @Test
    public void Can_wait_until_valid_result_is_returned() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = "valid";

        // Given
        options.waitFor(new ValidResult());
        given(until.success()).willReturn(someString(), someString(), expected);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = someString();

        // Given
        options.waitFor(new ValidResult());
        given(until.success()).willReturn(expected);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, atLeast(2)).success();
    }

    @Test
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_an_an_exception_was_thrown() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = someString();

        // Given
        options.waitFor(new ValidResult());
        given(until.success()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = new Waiter(options).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, atLeast(2)).success();
    }
}
