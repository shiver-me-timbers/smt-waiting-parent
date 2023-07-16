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
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.test.WaitingPropertyRuleAware;
import shiver.me.timbers.waiting.validation.FailResult;
import shiver.me.timbers.waiting.validation.SuccessResult;
import shiver.me.timbers.waiting.validation.ValidResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class AbstractITWaiterWaitForProperty extends AbstractITWaiterWaitFor
    implements ITWaiterDefaults, WaitingPropertyRuleAware {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @SuppressWarnings("rawtypes")
    @Override
    public WaitingFor waitFor(final long duration, final TimeUnit unit, final ResultValidator validator) {
        return new WaitingFor() {
            @Override
            public <T> T waitForMethod(Callable<T> callable) throws Exception {
                properties().addTimeout(duration, unit);
                properties().setProperty("smt.waiting.waitFor", validator.getClass().getName());
                return defaults().defaultsMethod(callable);
            }
        };
    }

    @SuppressWarnings({"SameParameterValue", "rawtypes"})
    protected abstract WaitingFor addWaitFor(long duration, TimeUnit unit, ResultValidator validator);

    @SuppressWarnings({"SameParameterValue", "rawtypes"})
    protected abstract WaitingFor clearThenAddWaitFor(
        long duration,
        TimeUnit unit,
        boolean clearWaitFor,
        ResultValidator validator
    );

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Can_set_multiple_wait_for_with_a_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = "valid success";

        // Given
        properties().setProperty("smt.waiting.timeout.duration", "500");
        properties().setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        properties().setProperty("smt.waiting.waitFor", format("%s,%s",
            ValidResult.class.getName(),
            SuccessResult.class.getName()
        ));
        given(callable.call()).willReturn("valid", "success", expected);

        // When
        final Object actual = defaults().defaultsMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Can_add_extra_wait_for_to_those_set_with_the_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = "valid success";

        // Given
        properties().setProperty("smt.waiting.waitFor", ValidResult.class.getName());
        given(callable.call()).willReturn("valid", "success", expected);

        // When
        final Object actual = addWaitFor(500L, MILLISECONDS, new SuccessResult()).waitForMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Can_clear_the_wait_for_properties_and_add_new_validator() throws Throwable {

        final Callable callable = mock(Callable.class);

        final Object expected = "success";

        // Given
        properties().setProperty("smt.waiting.waitFor", format("%s,%s",
            ValidResult.class.getName(),
            FailResult.class.getName()
        ));
        given(callable.call()).willReturn("path", "to", expected);

        // When
        final Object actual = clearThenAddWaitFor(500L, MILLISECONDS, true, new SuccessResult()).waitForMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(3)).call();
    }
}
