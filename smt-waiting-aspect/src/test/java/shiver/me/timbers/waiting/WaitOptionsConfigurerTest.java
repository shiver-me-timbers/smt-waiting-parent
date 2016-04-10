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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLongBetween;
import static shiver.me.timbers.waiting.Decision.UNDECIDED;
import static shiver.me.timbers.waiting.Decision.YES;

public class WaitOptionsConfigurerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private OptionsService options;
    private WaitOptionsConfigurer configurer;
    private Wait wait;

    private Timeout defaultTimeout;
    private Interval defaultInterval;
    private Decision defaultWaitForTrue;
    private Decision defaultWaitForNotNull;
    private Class<ResultValidator>[] defaultWaitFor;
    private Class<? extends Throwable>[] defaultInclude;
    private Class<? extends Throwable>[] defaultExclude;
    private boolean defaultWithDefaults;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        options = mock(OptionsService.class);
        wait = mock(Wait.class);
        configurer = new WaitOptionsConfigurer();

        defaultTimeout = mock(Timeout.class);
        given(defaultTimeout.duration()).willReturn(-1L);
        defaultInterval = mock(Interval.class);
        given(defaultInterval.duration()).willReturn(-1L);
        defaultWaitForTrue = UNDECIDED;
        defaultWaitForNotNull = UNDECIDED;
        defaultWaitFor = new Class[0];
        defaultInclude = new Class[0];
        defaultExclude = new Class[0];
        defaultWithDefaults = false;
    }

    @Test
    public void Can_configure_the_default_values() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verifyZeroInteractions(options);
    }

    @Test
    public void Can_configure_the_timeout() {

        final Timeout timeout = mock(Timeout.class);
        final Long duration = someLongBetween(0L, 100L);
        final TimeUnit unit = someEnum(TimeUnit.class);

        // Given
        given(wait.value()).willReturn(timeout);
        given(timeout.duration()).willReturn(duration);
        given(timeout.unit()).willReturn(unit);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).withTimeout(duration, unit);
        verifyNoMoreInteractions(options);
    }

    @Test
    public void Can_configure_the_interval() {

        final Interval interval = mock(Interval.class);

        final Long duration = someLongBetween(0L, 100L);
        final TimeUnit unit = someEnum(TimeUnit.class);

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(interval);
        given(interval.duration()).willReturn(duration);
        given(interval.unit()).willReturn(unit);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).withInterval(duration, unit);
        verifyNoMoreInteractions(options);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_configure_what_to_wait_for() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(new Class[]{TestResultOne.class, TestResultTwo.class});
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options, times(2)).waitFor(any(TestResultOne.class));
        verify(options, times(2)).waitFor(any(TestResultTwo.class));
        verifyNoMoreInteractions(options);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Cannot_configure_what_to_wait_for_if_the_result_validator_does_not_have_a_default_constructor() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(new Class[]{TestResultOne.class, NoDefaultTestResult.class});
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(InstantiationException.class));

        // When
        configurer.apply(options, wait);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Cannot_configure_what_to_wait_for_if_the_result_validator_does_not_have_a_public_constructor() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(new Class[]{TestResultOne.class, NoPublicTestResult.class});
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(IllegalAccessException.class));

        // When
        configurer.apply(options, wait);
    }

    @Test
    public void Can_configure_wait_for_true() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(YES);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).willWaitForTrue(true);
        verifyNoMoreInteractions(options);
    }

    @Test
    public void Can_configure_wait_for_not_null() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(YES);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).willWaitForNotNull(true);
        verifyNoMoreInteractions(options);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_configure_includes() {

        final Class<? extends Throwable> throwable1 = IllegalArgumentException.class;
        final Class<? extends Throwable> throwable2 = IllegalStateException.class;

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(new Class[]{throwable1, throwable2});
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).includes(throwable1, throwable2);
        verifyNoMoreInteractions(options);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_configure_excludes() {

        final Class<? extends Throwable> throwable1 = IllegalArgumentException.class;
        final Class<? extends Throwable> throwable2 = IllegalStateException.class;

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(new Class[]{throwable1, throwable2});
        given(wait.withDefaults()).willReturn(defaultWithDefaults);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).excludes(throwable1, throwable2);
        verifyNoMoreInteractions(options);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_configure_with_defaults() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitForTrue()).willReturn(defaultWaitForTrue);
        given(wait.waitForNotNull()).willReturn(defaultWaitForNotNull);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.includes()).willReturn(defaultInclude);
        given(wait.excludes()).willReturn(defaultExclude);
        given(wait.withDefaults()).willReturn(true);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).withDefaults(true);
        verifyNoMoreInteractions(options);
    }

    public static class TestResultOne implements ResultValidator {
        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }

    public static class TestResultTwo implements ResultValidator {
        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }

    public static class NoDefaultTestResult implements ResultValidator {

        @SuppressWarnings("UnusedParameters")
        public NoDefaultTestResult(Object object) {
        }

        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }

    public static class NoPublicTestResult implements ResultValidator {

        public NoPublicTestResult() throws IllegalAccessException {
            throw new IllegalAccessException();
        }

        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }
}