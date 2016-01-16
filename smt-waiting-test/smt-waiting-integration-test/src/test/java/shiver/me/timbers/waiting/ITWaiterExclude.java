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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public class ITWaiterExclude {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private Options options;
    private Waiter waiter;

    @Before
    public void setUp() {
        options = new Options().withTimeout(500L, MILLISECONDS);
        waiter = new Waiter(options);
    }

    @Test
    public void Cannot_ignore_exceptions_that_are_not_contained_in_the_exclude_list() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable expected = someOtherThrowable();

        // Given
        options.exclude(someThrowable().getClass())
            .exclude(expected.getClass())
            .exclude(someThrowable().getClass());
        given(until.success()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_ignore_exceptions_not_contained_in_the_exclude_list() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable include1 = someThrowable();
        final Throwable include2 = someThrowable();
        final Throwable include3 = someThrowable();

        final Object expected = new Object();

        // Given
        options.exclude(someOtherThrowable().getClass())
            .exclude(someOtherThrowable().getClass())
            .exclude(someOtherThrowable().getClass());
        given(until.success()).willThrow(include1, include2, include3).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(4)).success();
    }

    @Test
    public void Excludes_take_precedence_over_includes() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable expected = someThrowable();

        // Given
        options.include(expected.getClass()).exclude(expected.getClass());
        given(until.success()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        waiter.wait(until);
    }
}
