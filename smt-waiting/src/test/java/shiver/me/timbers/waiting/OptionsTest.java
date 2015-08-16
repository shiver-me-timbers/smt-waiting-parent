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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyReflectionEquals;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.waiting.HasFieldMatcher.hasField;

public class OptionsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private Sleeper sleeper;
    private PropertyParser propertyParser;
    private Options options;

    @Before
    public void setUp() {
        sleeper = mock(Sleeper.class);
        propertyParser = mock(PropertyParser.class);
        options = new Options(sleeper, propertyParser);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_create_an_options() throws InterruptedException {

        final long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        final ResultValidator validator = mock(ResultValidator.class);

        // Given
        given(propertyParser.getLongProperty("smt.waiting.timeoutDuration", 10L)).willReturn(timeoutDuration);
        given(propertyParser.getEnumProperty("smt.waiting.timeoutUnit", SECONDS)).willReturn(timeoutUnit);
        given(propertyParser.getLongProperty("smt.waiting.intervalDuration", 100L)).willReturn(intervalDuration);
        given(propertyParser.getEnumProperty("smt.waiting.intervalUnit", MILLISECONDS)).willReturn(intervalUnit);
        given(propertyParser.getBooleanProperty("smt.waiting.forTrue", false)).willReturn(true);
        given(propertyParser.getBooleanProperty("smt.waiting.forNotNull", false)).willReturn(true);
        given(propertyParser.getInstanceProperty("smt.waiting.for", null)).willReturn(validator);

        // When
        Options options = new Options(sleeper, propertyParser);

        // Then
        assertThat(options, hasField("timeoutDuration", is(timeoutDuration)));
        assertThat(options, hasField("timeoutUnit", is(timeoutUnit)));
        assertThat(options, hasField("intervalDuration", is(intervalDuration)));
        assertThat(options, hasField("intervalUnit", is(intervalUnit)));
        assertThat(options, hasField("resultValidators",
            contains(
                instanceOf(TrueResult.class),
                instanceOf(NotNullResult.class),
                is(validator)
            )
        ));
    }

    @Test
    public void Can_start_a_timeout() {

        // Given
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        // When
        final Timer actual = options.withTimeOut(duration, unit).startTimer();

        // Then
        assertPropertyReflectionEquals("duration", duration, actual);
        assertPropertyReflectionEquals("unit", unit, actual);
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
        final boolean actual = options.waitFor(resultValidator).isValid(result);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_have_no_result_validation() throws Throwable {

        // When
        final boolean actual = options.isValid(null);

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
        final boolean actual = options
            .waitFor(resultValidator1)
            .waitFor(resultValidator2)
            .waitFor(resultValidator3)
            .isValid(result);

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
        final boolean actual = options.waitFor(resultValidator1).waitFor(resultValidator2).waitFor(resultValidator3)
            .isValid(result);

        // Then
        assertThat(actual, is(false));
        verify(resultValidator1).isValid(result);
        verify(resultValidator2).isValid(result);
        verifyZeroInteractions(resultValidator3);
    }

    @Test
    public void Can_wait_for_a_true_result() throws Throwable {

        // Given
        final Boolean expected = someBoolean();

        // When
        final boolean actual = options.willWaitForTrue().isValid(expected);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_wait_for_a_true_result_that_is_not_a_boolean() throws Throwable {

        // When
        final boolean actual = options.willWaitForTrue().isValid(someString());

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void Can_wait_for_a_true_result_that_is_null() throws Throwable {

        // When
        final boolean actual = options.willWaitForTrue().isValid(null);

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void Can_wait_for_non_null_result() throws Throwable {

        // When
        final boolean actual = options.willWaitForNotNull().isValid(new Object());

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void Can_wait_for_non_null_result_that_is_null() throws Throwable {

        // When
        final boolean actual = options.willWaitForNotNull().isValid(null);

        // Then
        assertThat(actual, is(false));
    }

    @Test
    public void Can_pause_for_an_interval() throws InterruptedException {

        // Given
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // When
        options.withInterval(intervalDuration, intervalUnit).interval();

        // Then
        verify(sleeper).sleep(intervalUnit.toMillis(intervalDuration));
    }

    @Test
    public void Can_have_an_interval_interrupted() throws InterruptedException {

        expectedException.expect(RuntimeException.class);
        expectedException.expectCause(isA(InterruptedException.class));

        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // Given
        willThrow(new InterruptedException()).given(sleeper).sleep(intervalUnit.toMillis(intervalDuration));

        // When
        options.withInterval(intervalDuration, intervalUnit).interval();
    }

    @Test
    public void Can_pause_for_a_default_interval_of_100_milliseconds() throws InterruptedException {

        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // Given
        given(propertyParser.getLongProperty("smt.waiting.intervalDuration", 100L)).willReturn(intervalDuration);
        given(propertyParser.getEnumProperty("smt.waiting.intervalUnit", MILLISECONDS)).willReturn(intervalUnit);

        // When
        new Options(sleeper, propertyParser).interval();

        // Then
        verify(sleeper).sleep(intervalUnit.toMillis(intervalDuration));
    }
}