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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class ITWaiterAspectInclude {

    protected abstract ExpectedException expectedException();

    protected abstract WaitingIncludeComponent includeComponent();

    @Test
    public void Can_ignore_exceptions_contained_in_the_include_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willThrow(new TestIncludeRuntimeException(), new TestIncludeRuntimeException())
            .willReturn(expected);

        // When
        final Object actual = includeComponent().includeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    public void Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable expected = new TestTimeoutRuntimeException();

        // Given
        given(callable.call()).willThrow(expected);
        expectedException().expect(is(expected));

        // When
        includeComponent().includeMethod(callable);
    }
}
