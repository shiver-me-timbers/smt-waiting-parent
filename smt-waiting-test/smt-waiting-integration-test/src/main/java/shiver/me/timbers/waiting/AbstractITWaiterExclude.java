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

import java.util.List;
import java.util.concurrent.Callable;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public abstract class AbstractITWaiterExclude implements ITWaiterExclude {

    @Test
    @Override
    public void Cannot_ignore_exceptions_that_are_contained_in_the_exclude_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable expected = SOME_OTHER_THROWABLES[0];

        // Given
        given(callable.call()).willThrow(expected);
        expectedException().expect(is(expected));

        // When
        excludes(500L, MILLISECONDS, SOME_THROWABLES[0], expected, SOME_THROWABLES[1]).excludeMethod(callable);
    }

    @Test
    @Override
    public void Can_ignore_exceptions_that_are_not_contained_in_the_exclude_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = new Object();

        // Given
        given(callable.call()).willThrow(SOME_THROWABLES).willReturn(expected);

        // When
        final Object actual = excludes(500L, MILLISECONDS, SOME_OTHER_THROWABLES).excludeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(4)).call();
    }

    @Test
    @Override
    public void Cannot_ignore_exceptions_contained_in_the_exclude_list_and_not_in_the_include_list() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable exclude = someThrowable();

        // Given
        given(callable.call()).willThrow(exclude);
        expectedException().expect(is(exclude));

        // When
        excludesWithIncludes(500L, MILLISECONDS, asList(SOME_THROWABLES), asList(SOME_OTHER_THROWABLES)).excludeMethod(callable);
    }

    @Test
    @Override
    public void Excludes_take_precedence_over_includes() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable expected = SOME_THROWABLES[0];
        final List<Throwable> expectation = singletonList(expected);

        // Given
        given(callable.call()).willThrow(expected);
        expectedException().expect(is(expected));

        // When
        excludesWithIncludes(500L, MILLISECONDS, expectation, expectation).excludeMethod(callable);
    }
}
