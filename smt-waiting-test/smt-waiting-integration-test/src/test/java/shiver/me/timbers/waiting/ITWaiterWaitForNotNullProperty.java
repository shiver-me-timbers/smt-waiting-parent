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
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ITWaiterWaitForNotNullProperty {

    private PropertyManager properties;

    private Options options;
    private Waiter waiter;

    @Before
    public void setUp() {
        options = new Options().withTimeout(500L, MILLISECONDS);
        waiter = new Waiter(options);

        properties = new PropertyManager();
    }

    @After
    public void tearDown() {
        properties.restoreProperties();
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
}
