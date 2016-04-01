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

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public abstract class AbstractITWaiterInclude implements ITWaiterInclude {

    @Test
    @Override
    public void Can_ignore_exceptions_contained_in_the_include_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable include1 = someThrowable();
        final Throwable include2 = someThrowable();
        final Throwable include3 = someThrowable();

        final Object expected = new Object();

        // Given
        given(callable.call()).willThrow(include1, include2, include3).willReturn(expected);

        // When
        final Object actual = include(500L, MILLISECONDS, include1, include2, include3).includeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(4)).call();
    }

    @Test
    @Override
    public void Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable expected = someOtherThrowable();

        // Given
        given(callable.call()).willThrow(expected);
        expectedException().expect(is(expected));

        // When
        include(500L, MILLISECONDS, someThrowable(), someThrowable(), someThrowable()).includeMethod(callable);
    }

    @Test
    @Override
    public void Can_ignore_all_exceptions_if_no_includes_set() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willThrow(someThrowable(), someThrowable(), someThrowable()).willReturn(expected);

        // When
        final Object actual = include(500L, MILLISECONDS).includeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(4)).call();
    }

    @Test
    @Override
    public void Can_ignore_exceptions_contained_in_the_include_list_and_not_in_the_exclude_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final ArrayList<Throwable> includes = new ArrayList<Throwable>() {{
            add(someThrowable());
            add(someThrowable());
            add(someThrowable());
        }};
        final ArrayList<Throwable> excludes = new ArrayList<Throwable>() {{
            add(someOtherThrowable());
            add(someOtherThrowable());
            add(someOtherThrowable());
        }};

        final Object expected = new Object();

        // Given
        given(callable.call()).willThrow(includes.toArray(new Throwable[3])).willReturn(expected);

        // When
        final Object actual = includeWithExclude(500L, MILLISECONDS, includes, excludes).includeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(4)).call();
    }
}
