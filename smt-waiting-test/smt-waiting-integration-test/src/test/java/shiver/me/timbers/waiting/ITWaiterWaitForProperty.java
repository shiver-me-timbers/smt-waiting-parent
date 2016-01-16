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

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class ITWaiterWaitForProperty {

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
}
