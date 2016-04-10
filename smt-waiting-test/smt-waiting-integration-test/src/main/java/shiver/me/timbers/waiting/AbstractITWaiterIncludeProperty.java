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
import shiver.me.timbers.waiting.execution.PropertyWaitingIncludeAndExclude;
import shiver.me.timbers.waiting.execution.WaitingInclude;
import shiver.me.timbers.waiting.test.WaitingPropertyRuleAware;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.random.RandomExceptions.someThrowable;

public abstract class AbstractITWaiterIncludeProperty extends AbstractITWaiterInclude
    implements ITWaiterDefaults, WaitingPropertyRuleAware {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Override
    public ExpectedException expectedException() {
        return expectedException;
    }

    @Override
    public WaitingInclude includes(final long duration, final TimeUnit unit, final Throwable... includes) {
        return includesWithExcludes(duration, unit, asList(includes), Collections.<Throwable>emptyList());
    }

    @Override
    public WaitingInclude includesWithExcludes(
        final long duration,
        final TimeUnit unit,
        final List<Throwable> includes,
        final List<Throwable> excludes
    ) {
        return new PropertyWaitingIncludeAndExclude(properties(), duration, unit, includes, excludes) {
            @Override
            protected <T> T method(Callable<T> callable) throws Exception {
                return defaults().defaultsMethod(callable);
            }
        };
    }

    protected abstract WaitingInclude addInclude(long duration, TimeUnit unit, Throwable include);

    @Test
    public void Can_set_multiple_includes_with_a_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable exception1 = someThrowable();
        final Throwable exception2 = someThrowable();

        final Object expected = new Object();

        // Given
        properties().setProperty("smt.waiting.timeout.duration", "500");
        properties().setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        properties().setProperty("smt.waiting.include", format("%s,%s",
            exception1.getClass().getName(),
            exception2.getClass().getName()
        ));
        given(callable.call()).willThrow(exception1).willThrow(exception2).willReturn(expected);

        // When
        final Object actual = defaults().defaultsMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    public void Can_add_an_extra_include_to_those_set_with_the_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable exception1 = someThrowable();
        final Throwable exception2 = SOME_OTHER_THROWABLES[0];

        final Object expected = new Object();

        // Given
        properties().setProperty("smt.waiting.include", exception1.getClass().getName());
        given(callable.call()).willThrow(exception1).willThrow(exception2).willReturn(expected);

        // When
        final Object actual = addInclude(500L, MILLISECONDS, exception2).includeMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }
}
