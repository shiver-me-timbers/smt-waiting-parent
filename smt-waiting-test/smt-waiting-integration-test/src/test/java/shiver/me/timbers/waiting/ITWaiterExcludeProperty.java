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

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public class ITWaiterExcludeProperty {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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
    public void Can_set_exclude_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable expected = someOtherThrowable();

        // Given
        properties.setProperty("smt.waiting.exclude", expected.getClass().getName());
        given(until.success()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_set_multiple_includes_with_a_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable expected = someOtherThrowable();

        // Given
        properties.setProperty("smt.waiting.exclude", format("%s,%s",
            someThrowable().getClass().getName(),
            expected.getClass().getName()
        ));
        given(until.success()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        waiter.wait(until);
    }

    @Test
    public void Can_add_an_extra_include_to_those_set_with_the_system_property() throws Throwable {

        final Until until = mock(Until.class);

        final Throwable expected = someOtherThrowable();

        // Given
        properties.setProperty("smt.waiting.exclude", someThrowable().getClass().getName());
        options.exclude(expected.getClass());
        given(until.success()).willThrow(expected);
        expectedException.expect(is(expected));

        // When
        waiter.wait(until);
    }
}
