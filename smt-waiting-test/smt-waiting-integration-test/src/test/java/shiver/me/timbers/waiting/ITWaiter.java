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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomThings.someThing;

public class ITWaiter {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private PropertyManager properties;

    private Options options;
    private Waiter waiter;
    private Waiter shortWaiter;

    @Before
    public void setUp() {
        options = new Options().withTimeOut(500L, MILLISECONDS);
        waiter = new Waiter(options);
        shortWaiter = new Waiter(new Options().withTimeOut(10L, MILLISECONDS));

        properties = new PropertyManager();
    }

    @After
    public void tearDown() {
        properties.restoreProperties();
    }

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willThrow(new Exception()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_no_exception_is_thrown_with_defaults() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willThrow(new Exception()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final Until until = mock(Until.class);

        final Exception exception = new TestTimeOutException();

        // Given
        given(until.success()).willThrow(exception);
        expectedException.expect(WaitedTooLongException.class);
        expectedException.expectCause(is(exception));

        // When
        shortWaiter.wait(until);
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        final Until until = mock(Until.class);

        final Exception exception = new TestTimeOutRuntimeException();

        // Given
        given(until.success()).willThrow(exception);
        expectedException.expect(is(exception));

        // When
        shortWaiter.wait(until);
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        final Until until = mock(Until.class);

        final Error error = new TestTimeOutError();

        // Given
        given(until.success()).willThrow(error);
        expectedException.expect(is(error));

        // When
        shortWaiter.wait(until);
    }

    @Test
    public void Can_wait_until_valid_result_is_returned() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = "valid";

        // Given
        options.waitFor(new ValidResult());
        given(until.success()).willReturn(someString(), someString(), expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = someString();

        // Given
        options.waitFor(new ValidResult());
        given(until.success()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, atLeast(2)).success();
    }

    @Test
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_an_an_exception_was_thrown() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = someString();

        // Given
        options.waitFor(new ValidResult());
        given(until.success()).willThrow(new Exception()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, atLeast(2)).success();
    }

    @Test
    public void Can_wait_until_true_is_returned() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        options.willWaitForTrue(true);
        given(until.success()).willReturn(false, false, true);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is((Object) true));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_time_out_for_true_when_false_always_returned() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        options.willWaitForTrue(true);
        given(until.success()).willReturn(false);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is((Object) false));
        verify(until, atLeast(2)).success();
    }

    @Test
    public void Can_wait_until_time_out_for_true_when_null_always_returned() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        options.willWaitForTrue(true);
        given(until.success()).willReturn(null);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, nullValue());
        verify(until, atLeast(2)).success();
    }

    @Test
    public void Can_wait_for_a_non_null_value() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        options.willWaitForNotNull(true);
        given(until.success()).willReturn(null, null, expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_time_out_for_non_null_when_null_always_returned() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        options.willWaitForNotNull(true);
        given(until.success()).willReturn(null);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, nullValue());
        verify(until, atLeast(2)).success();
    }

    @Test
    public void Can_change_the_interval() throws Throwable {

        final Until until = mock(Until.class);
        final long start = System.currentTimeMillis();

        // Given
        options.withInterval(200L, MILLISECONDS);
        given(until.success()).willThrow(new Exception()).willReturn(new Object());

        // When
        waiter.wait(until);

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }

    @Test
    public void Can_ignore_exceptions_contained_in_the_include_list() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable include1 = someThrowable();
        final Throwable include2 = someThrowable();
        final Throwable include3 = someThrowable();

        final Object expected = new Object();

        // Given
        options.include(include1.getClass()).include(include2.getClass()).include(include3.getClass());
        given(until.success()).willThrow(include1, include2, include3).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(4)).success();
    }

    @Test
    public void Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable expected = someOtherThrowable();

        // Given
        options.include(someThrowable().getClass())
            .include(someThrowable().getClass())
            .include(someThrowable().getClass());
        given(until.success()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_ignore_all_exceptions_if_no_includes_set() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willThrow(someThrowable(), someThrowable(), someThrowable()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(4)).success();
    }

    @Test
    public void Can_configure_waiter_timeout_with_system_properties() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", "SECONDS");
        properties.setProperty("smt.waiting.interval.duration", "500");
        properties.setProperty("smt.waiting.interval.unit", "MILLISECONDS");
        given(until.success()).willThrow(new Exception());

        // When
        try {
            waiter.wait(until);
        } catch (Exception e) {
            // We only care about the number of calls, not how it failed.
        }

        // Then
        verify(until, atMost(3)).success();
    }

    @Test
    public void Can_override_the_duration_system_properties() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", "MINUTE");
        properties.setProperty("smt.waiting.interval.duration", "1");
        properties.setProperty("smt.waiting.interval.unit", "SECONDS");
        options.withTimeOut(1L, SECONDS).withInterval(500L, MILLISECONDS);
        given(until.success()).willThrow(new Exception());

        // When
        try {
            waiter.wait(until);
        } catch (Exception e) {
            // We only care about the number of calls, not how it failed.
        }

        // Then
        verify(until, atMost(3)).success();
    }

    @Test
    public void Can_handle_invalid_time_unit_property() throws Throwable {

        final Until until = mock(Until.class);

        final String invalidTimeUnit = someString();

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", invalidTimeUnit);
        given(until.success()).willThrow(new Exception());
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString(invalidTimeUnit));

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_set_wait_for_true_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        properties.setProperty("smt.waiting.waitForTrue", "true");
        given(until.success()).willReturn(false, false, true);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is((Object) true));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_override_the_wait_for_true_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitForTrue", "true");
        final Until until = mock(Until.class);

        // Given
        options.willWaitForTrue(false);
        given(until.success()).willReturn(false);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is((Object) false));
        verify(until).success();
    }

    @Test
    public void Can_set_wait_for_not_null_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        properties.setProperty("smt.waiting.waitForNotNull", "true");
        given(until.success()).willReturn(null, null, expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_override_the_wait_for_not_null_system_property() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        properties.setProperty("smt.waiting.waitForNotNull", "true");
        options.willWaitForNotNull(false);
        given(until.success()).willReturn(null);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, nullValue());
        verify(until).success();
    }

    @Test
    public void Can_set_wait_for_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = "valid";

        // Given
        properties.setProperty("smt.waiting.waitFor", ValidResult.class.getName());
        given(until.success()).willReturn(someString(), someString(), expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_set_multiple_wait_for_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = "valid success";

        // Given
        properties.setProperty("smt.waiting.waitFor", format("%s,%s",
            ValidResult.class.getName(),
            SuccessResult.class.getName()
        ));
        given(until.success()).willReturn("valid", "success", expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_add_extra_wait_for_to_those_set_with_the_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = "valid success";

        // Given
        properties.setProperty("smt.waiting.waitFor", ValidResult.class.getName());
        options.waitFor(new SuccessResult());
        given(until.success()).willReturn("valid", "success", expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_set_include_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable exception = someThrowable();

        final Object expected = new Object();

        // Given
        properties.setProperty("smt.waiting.include", exception.getClass().getName());
        given(until.success()).willThrow(exception).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).success();
    }

    @Test
    public void Can_set_multiple_includes_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable exception1 = someThrowable();
        final Throwable exception2 = someThrowable();

        final Object expected = new Object();

        // Given
        properties.setProperty("smt.waiting.include", format("%s,%s",
            exception1.getClass().getName(),
            exception2.getClass().getName()
        ));
        given(until.success()).willThrow(exception1).willThrow(exception2).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_add_an_extra_include_to_those_set_with_the_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable exception1 = someThrowable();
        final Throwable exception2 = someThrowable();

        final Object expected = new Object();

        // Given
        properties.setProperty("smt.waiting.include", exception1.getClass().getName());
        options.include(exception2.getClass());
        given(until.success()).willThrow(exception1).willThrow(exception2).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_reset_values_back_to_defaults() throws Throwable {

        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "30");
        properties.setProperty("smt.waiting.timeout.unit", "SECONDS");
        properties.setProperty("smt.waiting.interval.duration", "10");
        properties.setProperty("smt.waiting.interval.unit", "SECONDS");
        properties.setProperty("smt.waiting.waitForTrue", "true");
        properties.setProperty("smt.waiting.waitForNotNull", "true");
        properties.setProperty("smt.waiting.waitFor", NoResult.class.getName());
        properties.setProperty("smt.waiting.include", IllegalArgumentException.class.getName());
        properties.setProperty("smt.waiting.exclude", IllegalStateException.class.getName());
        options.withDefaults(true);
        given(until.success()).willThrow(new IllegalStateException()).willReturn(expected);

        // When
        final Object actual = waiter.wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).success();
    }

    private static Throwable someThrowable() {
        return someThing(new Exception(), new RuntimeException(), new Error(), new IllegalArgumentException());
    }

    private static Throwable someOtherThrowable() {
        return someThing(
            new IllegalStateException(),
            new ClassCastException(),
            new NumberFormatException(),
            new IllegalAccessError()
        );
    }
}
