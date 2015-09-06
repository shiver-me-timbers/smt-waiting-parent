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

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
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
    public void Can_create_a_default_options() {

        // When
        new Options();
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
        given(propertyParser.getLongProperty("smt.waiting.timeout.duration", 10L)).willReturn(timeoutDuration);
        given(propertyParser.getEnumProperty("smt.waiting.timeout.unit", SECONDS)).willReturn(timeoutUnit);
        given(propertyParser.getLongProperty("smt.waiting.interval.duration", 100L)).willReturn(intervalDuration);
        given(propertyParser.getEnumProperty("smt.waiting.interval.unit", MILLISECONDS)).willReturn(intervalUnit);
        given(propertyParser.getBooleanProperty("smt.waiting.waitForTrue", false)).willReturn(true);
        given(propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", false)).willReturn(true);
        given(propertyParser.getInstanceProperty("smt.waiting.waitFor")).willReturn(Collections.<Object>singletonList(validator));

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
    public void Can_wait_for_a_true_result() throws Throwable {

        // When
        final Choice actual = options.willWaitForTrue().choose();

        // Then
        assertThat(actual, hasField("resultValidators", hasItem(isA(TrueResult.class))));
    }

    @Test
    public void Can_not_wait_for_a_true_result() throws Throwable {

        // When
        final Choice actual = options.willNotWaitForTrue().choose();

        // Then
        assertThat(actual, hasField("resultValidators", empty()));
    }

    @Test
    public void Can_override_wait_for_a_true_result() throws Throwable {

        // Given
        final AnyResult anyResult = new AnyResult();

        // When
        final Choice actual = options
            .waitFor(anyResult)
            .willWaitForTrue()
            .willNotWaitForTrue()
            .choose();

        // Then
        assertThat(actual, hasField("resultValidators", hasItem(is(anyResult))));
    }

    @Test
    public void Can_wait_for_non_null_result() throws Throwable {

        // When
        final Choice actual = options.willWaitForNotNull().choose();

        // Then
        assertThat(actual, hasField("resultValidators", hasItem(isA(NotNullResult.class))));
    }

    @Test
    public void Can_not_wait_for_non_null_result() throws Throwable {

        // When
        final Choice actual = options.willNotWaitForNotNull().choose();

        // Then
        assertThat(actual, hasField("resultValidators", empty()));
    }

    @Test
    public void Can_override_wait_for_non_null_result() throws Throwable {

        // Given
        final AnyResult anyResult = new AnyResult();

        // When
        final Choice actual = options
            .waitFor(anyResult)
            .willWaitForNotNull()
            .willNotWaitForNotNull()
            .choose();

        // Then
        assertThat(actual, hasField("resultValidators", hasItem(is(anyResult))));
    }

    @Test
    public void Can_pause_for_a_default_interval_of_100_milliseconds() throws InterruptedException {

        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // Given
        given(propertyParser.getLongProperty("smt.waiting.interval.duration", 100L)).willReturn(intervalDuration);
        given(propertyParser.getEnumProperty("smt.waiting.interval.unit", MILLISECONDS)).willReturn(intervalUnit);

        // When
        final Choice actual = new Options(sleeper, propertyParser).choose();

        // Then
        assertThat(actual, hasField("intervalDuration", is(intervalDuration)));
        assertThat(actual, hasField("intervalUnit", is(intervalUnit)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_reset_values_back_to_defaults() throws InterruptedException {

        final Long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final Long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        final ResultValidator validator = mock(ResultValidator.class);

        // Given
        given(propertyParser.getLongProperty("smt.waiting.timeout.duration", 10L)).willReturn(someLong());
        given(propertyParser.getEnumProperty("smt.waiting.timeout.unit", SECONDS)).willReturn(someEnum(TimeUnit.class));
        given(propertyParser.getLongProperty("smt.waiting.interval.duration", 100L)).willReturn(someLong());
        given(propertyParser.getEnumProperty("smt.waiting.interval.unit", MILLISECONDS))
            .willReturn(someEnum(TimeUnit.class));
        given(propertyParser.getBooleanProperty("smt.waiting.waitForTrue", false)).willReturn(someBoolean());
        given(propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", false)).willReturn(someBoolean());
        given(propertyParser.getInstanceProperty("smt.waiting.waitFor"))
            .willReturn(Collections.<Object>singletonList(mock(ResultValidator.class)));

        // When
        Options options = new Options(sleeper, propertyParser)
            .withDefaults()
            .withTimeOut(timeoutDuration, timeoutUnit)
            .withInterval(intervalDuration, intervalUnit)
            .willWaitForTrue()
            .willWaitForNotNull()
            .waitFor(validator);


        // Then
        assertThat(options, hasField("timeoutDuration", is(timeoutDuration)));
        assertThat(options, hasField("timeoutUnit", is(timeoutUnit)));
        assertThat(options, hasField("intervalDuration", is(intervalDuration)));
        assertThat(options, hasField("intervalUnit", is(intervalUnit)));
        assertThat(options, hasField("resultValidators", containsInAnyOrder(
            instanceOf(TrueResult.class),
            instanceOf(NotNullResult.class),
            is(validator)
        )));
    }
}