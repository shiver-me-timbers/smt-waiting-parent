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
import static java.util.concurrent.TimeUnit.MINUTES;
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
        properties.backupProperty("smt.waiting.timeout.duration");
        properties.backupProperty("smt.waiting.timeout.unit");
        properties.backupProperty("smt.waiting.interval.duration");
        properties.backupProperty("smt.waiting.interval.unit");
        properties.backupProperty("smt.waiting.waitForNotNull");
        properties.backupProperty("smt.waiting.waitForTrue");
        properties.backupProperty("smt.waiting.waitFor");
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
        final Object actual = new Waiter().wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final Exception exception = new TestTimeOutException();

        expectedException.expect(WaitedTooLongException.class);
        expectedException.expectCause(is(exception));

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(exception);

        // When
        shortWaiter.wait(until);
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        final Exception exception = new TestTimeOutRuntimeException();

        expectedException.expect(is(exception));

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(exception);

        // When
        shortWaiter.wait(until);
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        final Error error = new TestTimeOutError();

        expectedException.expect(is(error));

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(error);

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
        options.willWaitForTrue();
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
        options.willWaitForTrue();
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
        options.willWaitForTrue();
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
        options.willWaitForNotNull();
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
        options.willWaitForNotNull();
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
    public void Can_configure_waiter_timeout_with_system_properties() throws Throwable {

        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", "SECONDS");
        properties.setProperty("smt.waiting.interval.duration", "500");
        properties.setProperty("smt.waiting.interval.unit", "MILLISECONDS");
        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(new Exception());

        // When
        try {
            new Waiter().wait(until);
        } catch (Exception e) {
            // We only care about the number of calls, not how it failed.
        }

        // Then
        verify(until, atMost(3)).success();
    }

    @Test
    public void Can_override_the_duration_system_properties() throws Throwable {

        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", "MINUTE");
        properties.setProperty("smt.waiting.interval.duration", "1");
        properties.setProperty("smt.waiting.interval.unit", "SECONDS");
        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(new Exception());

        // When
        try {
            new Waiter(new Options().withTimeOut(1L, SECONDS).withInterval(500L, MILLISECONDS)).wait(until);
        } catch (Exception e) {
            // We only care about the number of calls, not how it failed.
        }

        // Then
        verify(until, atMost(3)).success();
    }

    @Test
    public void Can_handle_invalid_time_unit_property() throws Throwable {

        final String invalidTimeUnit = someString();

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString(invalidTimeUnit));

        properties.setProperty("smt.waiting.timeout.duration", "1");
        properties.setProperty("smt.waiting.timeout.unit", invalidTimeUnit);
        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(new Exception());

        // When
        new Waiter().wait(until);
    }

    @Test
    public void Can_set_wait_for_true_with_a_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitForTrue", "true");
        final Until until = mock(Until.class);

        // Given
        given(until.success()).willReturn(false, false, true);

        // When
        final Object actual = new Waiter().wait(until);

        // Then
        assertThat(actual, is((Object) true));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_override_the_wait_for_true_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitForTrue", "true");
        final Until until = mock(Until.class);

        // Given
        given(until.success()).willReturn(false);

        // When
        final Object actual = new Waiter(new Options().willNotWaitForTrue()).wait(until);

        // Then
        assertThat(actual, is((Object) false));
        verify(until).success();
    }

    @Test
    public void Can_set_wait_for_not_null_with_a_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitForNotNull", "true");
        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willReturn(null, null, expected);

        // When
        final Object actual = new Waiter().wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_override_the_wait_for_not_null_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitForNotNull", "true");
        final Until until = mock(Until.class);

        // Given
        given(until.success()).willReturn(null);

        // When
        final Object actual = new Waiter(new Options().willNotWaitForNotNull()).wait(until);

        // Then
        assertThat(actual, nullValue());
        verify(until).success();
    }

    @Test
    public void Can_set_wait_for_with_a_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitFor", ValidResult.class.getName());
        final Until until = mock(Until.class);

        final Object expected = "valid";

        // Given
        given(until.success()).willReturn(someString(), someString(), expected);

        // When
        final Object actual = new Waiter().wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_set_multiple_wait_for_with_a_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitFor", format("%s,%s",
            ValidResult.class.getName(),
            SuccessResult.class.getName()
        ));
        final Until until = mock(Until.class);

        final Object expected = "valid success";

        // Given
        given(until.success()).willReturn("valid", "success", expected);

        // When
        final Object actual = new Waiter().wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_add_extra_wait_for_to_those_set_with_the_system_property() throws Throwable {

        properties.setProperty("smt.waiting.waitFor", ValidResult.class.getName());
        final Until until = mock(Until.class);

        final Object expected = "valid success";

        // Given
        given(until.success()).willReturn("valid", "success", expected);

        // When
        final Object actual = new Waiter(new Options().waitFor(new SuccessResult())).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(3)).success();
    }

    @Test
    public void Can_reset_values_back_to_defaults() throws Throwable {

        properties.setProperty("smt.waiting.timeout.duration", "30");
        properties.setProperty("smt.waiting.timeout.unit", "SECONDS");
        properties.setProperty("smt.waiting.interval.duration", "10");
        properties.setProperty("smt.waiting.interval.unit", "SECONDS");
        properties.setProperty("smt.waiting.waitForTrue", "true");
        properties.setProperty("smt.waiting.waitForNotNull", "true");
        properties.setProperty("smt.waiting.waitFor", NoResult.class.getName());
        final Until until = mock(Until.class);

        final Object expected = new Object();

        // Given
        given(until.success()).willReturn(expected);

        // When
        final Object actual = new Waiter(new Options().withDefaults()).wait(until);

        // Then
        assertThat(actual, is(expected));
        verify(until).success();
    }
}
