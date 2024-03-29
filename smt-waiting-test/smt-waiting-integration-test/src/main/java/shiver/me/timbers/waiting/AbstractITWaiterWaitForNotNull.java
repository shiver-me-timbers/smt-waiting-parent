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
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class AbstractITWaiterWaitForNotNull implements ITWaiterWaitForNotNull {

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void Can_wait_for_a_non_null_value() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willReturn(null, null, expected);

        // When
        final Object actual = waitForNotNull(500L, MILLISECONDS, true).waitForNotNullMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void Can_wait_until_time_out_for_non_null_when_null_always_returned() throws Throwable {

        final Callable callable = mock(Callable.class);

        // Given
        given(callable.call()).willReturn(null);

        // When
        final Object actual = waitForNotNull(200L, MILLISECONDS, true).waitForNotNullMethod(callable);

        // Then
        assertThat(actual, nullValue());
        verify(callable, atLeast(2)).call();
    }
}
