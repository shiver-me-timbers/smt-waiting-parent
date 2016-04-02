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

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public abstract class AbstractITWaiterExcludeProperty extends AbstractITWaiterExclude implements ITWaiterDefaults {

    @Rule
    public final WaitingPropertyRule properties = new WaitingPropertyRule();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Override
    public ExpectedException expectedException() {
        return expectedException;
    }

    @Override
    public WaitingExclude excludes(long duration, TimeUnit unit, Throwable... excludes) {
        return excludesWithIncludes(duration, unit, asList(excludes), Collections.<Throwable>emptyList());
    }

    @Override
    public WaitingExclude excludesWithIncludes(
        final long duration,
        final TimeUnit unit,
        final List<Throwable> excludes,
        final List<Throwable> includes
    ) {
        return new PropertyWaitingIncludeAndExclude(properties, duration, unit, includes, excludes) {
            @Override
            protected <T> T method(Callable<T> callable) throws Exception {
                return defaults().defaultsMethod(callable);
            }
        };
    }

    protected abstract WaitingExclude addExclude(long duration, TimeUnit unit, Throwable exclude);

    @Test
    public void Can_set_multiple_excludes_with_a_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable expected = someOtherThrowable();

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "500");
        properties.setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        properties.setProperty("smt.waiting.exclude", format("%s,%s",
            someThrowable().getClass().getName(),
            expected.getClass().getName()
        ));
        given(callable.call()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        defaults().defaultsMethod(callable);
    }

    @Test
    public void Can_add_an_extra_exclude_to_those_set_with_the_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Throwable expected = someOtherThrowable();

        // Given
        properties.setProperty("smt.waiting.exclude", someThrowable().getClass().getName());
        given(callable.call()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        addExclude(500L, MILLISECONDS, expected).excludeMethod(callable);
    }
}
