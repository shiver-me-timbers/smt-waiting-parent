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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.matchers.Matchers.hasField;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public class ChoiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_start_a_timeout() {

        // Given
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        // When
        final Timer actual = new Choice(
            mock(Sleeper.class),
            duration,
            unit,
            someLong(),
            someEnum(TimeUnit.class),
            Collections.<ResultValidator>emptyList(),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).startTimer();

        // Then
        assertThat(actual, hasField("duration", duration));
        assertThat(actual, hasField("unit", unit));
    }

    @Test
    public void Can_validate_result() throws Throwable {

        final Object result = new Object();

        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator = mock(ResultValidator.class);

        final boolean expected = someBoolean();

        // Given
        given(resultValidator.isValid(result)).willReturn(expected);

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(),
            someEnum(TimeUnit.class),
            Collections.<ResultValidator>singletonList(resultValidator),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).isValid(result);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_have_no_result_validation() throws Throwable {

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(),
            someEnum(TimeUnit.class),
            Collections.<ResultValidator>emptyList(),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).isValid(null);

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void Can_validate_result_in_multiple_ways() throws Throwable {

        final Object result = new Object();
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator1 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator2 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator3 = mock(ResultValidator.class);

        final boolean expected = someBoolean();

        // Given
        given(resultValidator1.isValid(result)).willReturn(true);
        given(resultValidator2.isValid(result)).willReturn(true);
        given(resultValidator3.isValid(result)).willReturn(expected);

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(),
            someEnum(TimeUnit.class),
            Arrays.<ResultValidator>asList(resultValidator1, resultValidator2, resultValidator3),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).isValid(result);

        // Then
        assertThat(actual, is(expected));
        verify(resultValidator1).isValid(result);
        verify(resultValidator2).isValid(result);
        verify(resultValidator3).isValid(result);
    }

    @Test
    public void Can_stop_on_first_result_validation_failure() throws Throwable {

        final Object result = new Object();
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator1 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator2 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator3 = mock(ResultValidator.class);

        // Given
        given(resultValidator1.isValid(result)).willReturn(true);
        given(resultValidator2.isValid(result)).willReturn(false);

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(),
            someEnum(TimeUnit.class),
            Arrays.<ResultValidator>asList(resultValidator1, resultValidator2, resultValidator3),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).isValid(result);

        // Then
        assertThat(actual, is(false));
        verify(resultValidator1).isValid(result);
        verify(resultValidator2).isValid(result);
        verifyZeroInteractions(resultValidator3);
    }

    @Test
    public void Can_pause_for_an_interval() throws InterruptedException {

        // Given
        final Sleeper sleeper = mock(Sleeper.class);
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // When
        new Choice(
            sleeper,
            someLong(),
            someEnum(TimeUnit.class),
            intervalDuration,
            intervalUnit,
            Collections.<ResultValidator>emptyList(),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).interval();

        // Then
        verify(sleeper).sleep(intervalUnit.toMillis(intervalDuration));
    }

    @Test
    public void Can_have_an_interval_interrupted() throws InterruptedException {

        expectedException.expect(RuntimeException.class);
        expectedException.expectCause(isA(InterruptedException.class));

        final Sleeper sleeper = mock(Sleeper.class);
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // Given
        willThrow(new InterruptedException()).given(sleeper).sleep(intervalUnit.toMillis(intervalDuration));

        // When
        new Choice(
            sleeper,
            someLong(),
            someEnum(TimeUnit.class),
            intervalDuration,
            intervalUnit,
            Collections.<ResultValidator>emptyList(),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).interval();
    }

    @Test
    public void Can_check_if_an_included_exception_is_suppressed() {

        // Given
        final Throwable exception = someOtherThrowable();

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(),
            someEnum(TimeUnit.class),
            Collections.<ResultValidator>emptyList(),
            new HashSet<>(asList(someThrowable().getClass(), exception.getClass(), someThrowable().getClass())),
            Collections.<Class<? extends Throwable>>emptySet()
        ).isSuppressed(exception);

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void Can_check_if_an_exception_that_is_not_included_is_not_suppressed() {

        // Given
        final Throwable exception = someOtherThrowable();

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(),
            someEnum(TimeUnit.class),
            Collections.<ResultValidator>emptyList(),
            new HashSet<>(asList(someThrowable().getClass(), someThrowable().getClass(), someThrowable().getClass())),
            Collections.<Class<? extends Throwable>>emptySet()
        ).isSuppressed(exception);

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void Can_check_that_all_exceptions_are_suppressed_if_no_includes_are_set() {

        // Given
        final Throwable exception = someThrowable();

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(),
            someEnum(TimeUnit.class),
            Collections.<ResultValidator>emptyList(),
            Collections.<Class<? extends Throwable>>emptySet(),
            Collections.<Class<? extends Throwable>>emptySet()
        ).isSuppressed(exception);

        // Then
        assertThat(actual, is(true));
    }
}