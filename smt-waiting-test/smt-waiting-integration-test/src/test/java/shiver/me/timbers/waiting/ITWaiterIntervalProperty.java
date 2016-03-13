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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class ITWaiterIntervalProperty {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private PropertyManager properties;

    private Options options;

    @Before
    public void setUp() {
        options = new Options().withTimeout(500L, MILLISECONDS);
        properties = new PropertyManager();
    }

    @After
    public void tearDown() {
        properties.restoreProperties();
    }

    @Test
    public void Can_configure_the_interval_with_system_properties() throws Throwable {

        final Until until = mock(Until.class);
        final long start = System.currentTimeMillis();

        // Given
        properties.setProperty("smt.waiting.interval.duration", "200");
        properties.setProperty("smt.waiting.interval.unit", "MILLISECONDS");
        given(until.success()).willThrow(new Exception()).willReturn(new Object());

        // When
        new Waiter(options).wait(until);

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }

    @Test
    public void Can_override_the_interval_system_properties() throws Throwable {

        final Until until = mock(Until.class);
        final long start = System.currentTimeMillis();

        // Given
        properties.setProperty("smt.waiting.interval.duration", "1");
        properties.setProperty("smt.waiting.interval.unit", "SECONDS");
        options.withInterval(200L, MILLISECONDS);
        given(until.success()).willThrow(new Exception()).willReturn(new Object());

        // When
        new Waiter(options).wait(until);

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }

    @Test
    public void Can_handle_invalid_time_unit_property() throws Throwable {

        final Until until = mock(Until.class);

        final String invalidTimeUnit = someString();

        // Given
        properties.setProperty("smt.waiting.interval.duration", "1");
        properties.setProperty("smt.waiting.interval.unit", invalidTimeUnit);
        given(until.success()).willThrow(new Exception());
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString(invalidTimeUnit));

        // When
        new Waiter(options).wait(until);
    }
}
