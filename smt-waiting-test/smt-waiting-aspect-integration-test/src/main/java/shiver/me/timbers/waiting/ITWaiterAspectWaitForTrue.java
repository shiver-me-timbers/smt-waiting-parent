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

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class ITWaiterAspectWaitForTrue {

    protected abstract WaitingForTrueComponent waitForTrueComponent();

    @Test
    public void Can_wait_until_true_is_returned() throws Throwable {

        @SuppressWarnings("unchecked")
        final Callable<Boolean> callable = mock(Callable.class);

        // Given
        given(callable.call()).willReturn(false, false, true);

        // When
        final boolean actual = waitForTrueComponent().waitForTrueMethod(callable);

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
        final boolean actual = waitForTrueComponent().waitForTrueMethod(callable);

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
        final Boolean actual = waitForTrueComponent().waitForTrueMethod(callable);

        // Then
        assertThat(actual, nullValue());
        verify(callable, atLeast(2)).call();
    }
}
