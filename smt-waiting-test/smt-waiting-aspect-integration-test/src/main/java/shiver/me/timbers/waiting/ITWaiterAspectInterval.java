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

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class ITWaiterAspectInterval {

    protected abstract WaitingIntervalComponent intervalComponent();

    @Test
    public void Can_change_the_interval() throws Throwable {

        final Callable callable = mock(Callable.class);
        final long start = System.currentTimeMillis();

        // Given
        given(callable.call()).willThrow(new Exception()).willReturn(new Object());

        // When
        intervalComponent().intervalMethod(callable);

        // Then
        verify(callable, times(2)).call();
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }
}
